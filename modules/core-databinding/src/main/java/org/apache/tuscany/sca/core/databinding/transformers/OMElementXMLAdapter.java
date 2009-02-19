package org.apache.tuscany.sca.core.databinding.transformers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.tuscany.sca.databinding.xml.XMLStreamReader2String;

/**
 * This special XmlAdapter can be used by JAXB classes to convert from OMElement to Source
 */
public class OMElementXMLAdapter extends XmlAdapter<Source, OMElement> {
	
	private TransformerFactory transformerFactory = TransformerFactory.newInstance();
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    
    @Override
    public Source marshal(OMElement v) throws Exception {
    	XMLStreamReader reader = v.getXMLStreamReaderWithoutCaching();
        XMLStreamReader2String t2 = new XMLStreamReader2String();
        String xml = t2.transform(reader, null);
        return new StreamSource(new StringReader(xml));    	
    }

    @Override
    public OMElement unmarshal(Source v) throws Exception {        
    	StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        transformerFactory.newTransformer().transform(v, result);
        XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(sw.toString())); 
        // Build OMElement from XMLStreamReader
        StAXOMBuilder builder = new StAXOMBuilder(reader);
        return builder.getDocumentElement();
    }
}
