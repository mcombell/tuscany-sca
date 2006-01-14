/**
 *
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tuscany.das.rdb.test.framework;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.sdo.EProperty;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Sequence;
import commonj.sdo.Type;

/**
 * Utility class capable of writing a SDO data graph to a String.  This tries as
 * much as possible to use only SDO interfaces but has to resort to Ecore in a few
 * places that are clearly labelled.  The code is a lightly edited version of a
 * class used both for testing and for RAS purposes within Jetstream.
 */
public class SDOPrinter {
  // Begin methods that require using Ecore
  /**
   * Check if a given feature is derived. If it is then
   * the feature is actually delegated to a feature map.
   */
  public static boolean isDerived(Property prop) {
    return ((EProperty) prop).getEStructuralFeature().isDerived();
  }
  
  /** 
   * Return the base feature of a derived feature
   */
  public static Property getBaseProperty(Property prop) {
    ExtendedMetaData meta = ExtendedMetaData.INSTANCE;
    EStructuralFeature feature = ((EProperty) prop).getEStructuralFeature();
    EStructuralFeature ans = meta.getMixedFeature(feature.getEContainingClass());
    if(ans == null) {
      ans = feature;
      while(ans.isDerived())
        ans = meta.getGroup(ans);
    }
    return (Property) prop.getType().getProperties().get(ans.getFeatureID());
  }
  // End methods that require using Ecore
    
  /**
   * Generate a string which references this data object (from the top of the data
   * graph). This is useful when we have a non-containment reference (so that we 
   * can print the path instead of following the reference into an infinite loop).
   */
  public static String ref(DataObject data) {
    String result = null;
    
    DataObject parent = data.getContainer();

    if( parent == null ) {
      result = "";

    } else {
      result = ref(parent);
      Property containment = data.getContainmentProperty();
      if (isDerived(containment))
        containment = getBaseProperty(containment); 
      result += '/' + containment.getName();
      if(containment.isMany())
        result += "[" + (((List) parent.get(containment)).indexOf(data)+1) + ']'; 
    }
    
    return result;
  }
  
  /**
   * Write this datagraph as a string. The output uses indentation to show the 
   * containment hierarchy of the data objects within the graph. 
   */
  public static String print( DataGraph graph ) {
    StringBuffer result = new StringBuffer();
    
    DataObject root = graph.getRootObject();

    result.append( print(root, 0) );
    return result.toString();
  }

  /**
   * Write a single data object to a string, using the current indentation. This
   * method recustively calls itself to print out the complete tree of nodes below
   * the current data.
   * 
   * <p>This routine does not handle sequenced data objetcs, they are delegated to
   * printsequence.
   * 
   * @see SDOPrinter#printSequence(DataObject, int, DataObjectInfo)
   */
  private static String print(DataObject data, int indent) {
    if( data == null ) {
      return "";
    }

    StringBuffer result = new StringBuffer();
    Type type = data.getType();
    String className = type.getName();
    result.append( "(DataObject: " + className + ") {\n" );
    
    List attrs = type.getProperties();
    for( int names = 0; names < attrs.size(); names++ ) {
      Property prop = (Property)attrs.get(names);
      String name = prop.getName();
      Object value = data.get(prop);
      boolean contained = prop.isContainment();
      boolean virtual = isDerived(prop);

      for( int i = 0; i < indent + 1; i++ ) {
        result.append( "  " );
      }
      result.append( name );
      result.append( "=" );

      if(value instanceof DataObject && (!contained || virtual)) {
        // Non containment refs and virtual features should not print out the
        // full data. Instead we print a ref to the actual data location.
        result.append( "<ref> " + ref((DataObject) value));
      } else {
        result.append( print(value, indent + 1) );
      }
      result.append( "\n" );
    }

    for( int i = 0; i < indent; i++ ) {
      result.append( "  " );
    }
    result.append( "}" );
    
    return result.toString();
  }

  /**
   * Write a to a string, using the current indentation. This
   * method recursively prints out the entire tree of nodes under 'seq'.
   */    
  private static String print(Sequence seq, int indent) {
    if( seq == null ) {
      return "";
    }
    if( seq.size() == 0 ) {
      return "(Sequence) []";
    }

    StringBuffer result = new StringBuffer();

    result.append( "(Sequence) [\n" );
    
    int limit = seq.size();
    
    for( int setting = 0; setting < limit; setting++ ) {
      Property prop = seq.getProperty(setting);
      String name = prop.getName();
      Object value = seq.getValue(setting);

      if( name == null ) {
        name = "<text>";
      }

      for( int i = 0; i < indent + 1; i++ ) {
        result.append( "  " );
      }
      result.append( name );
      result.append( "=" );
      
      result.append( print(value, indent + 1) );
      
      result.append( "\n" );
    }

    for( int i = 0; i < indent; i++ ) {
      result.append( "  " );
    }
    result.append( "]" );
    
    return result.toString();
  }

  /**
   * Print a SDO many-valued feature (list). This routine iterates through the
   * list, outputting each member at the current indentation level.
   */
  private static String print(List data, int indent) {
    if( data.size() == 0 ) {
      return "(List) []";
    }

    StringBuffer result = new StringBuffer();

    result.append("(List) [\n");

    Iterator elements = data.iterator();

    while( elements.hasNext() ) {
      Object element = elements.next();
              
      for( int i = 0; i < indent + 1; i++ ) {
        result.append( "  " );
      }
      result.append( print(element, indent + 1) );

      if( elements.hasNext() ) {
        result.append(",\n");
      } else {
        result.append("\n");
      }
    }

    for( int i = 0; i < indent; i++ ) {
      result.append( "  " );
    }
    result.append("]");

    return result.toString();
  }    
  
  /**
   * General puropose print routine. This routine inspects the data, and delegates
   * to a specialised print routine, or uses the default 'toString' behaviour of
   * the object.
   * 
   * <p>There are specialised routines for DataObjects, and Lists.
   */
  private static String print(Object data, int indent) {
    StringBuffer result = new StringBuffer();

    if( data instanceof List ) {
        
      result.append( print((List) data, indent) );
        
    } else if( data instanceof byte[] ){
    
      result.append( print( (byte[]) data, indent ) );
                
    } else if( data instanceof DataObject ) {

      result.append( print((DataObject) data, indent) );

    } else if( data instanceof Sequence ) {

      result.append( print((Sequence) data, indent) );

    } else {
      result.append("(Data)\'");
      result.append( data );
      result.append("'");
    }
    
    return result.toString();
  }
}
