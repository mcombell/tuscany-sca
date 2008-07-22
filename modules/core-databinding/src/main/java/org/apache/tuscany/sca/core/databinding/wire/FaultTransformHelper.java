package org.apache.tuscany.sca.core.databinding.wire;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.tuscany.sca.databinding.Mediator;
import org.apache.tuscany.sca.interfacedef.DataType;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.interfacedef.impl.DataTypeImpl;
import org.apache.tuscany.sca.interfacedef.util.FaultException;
import org.apache.tuscany.sca.interfacedef.util.XMLType;
import org.apache.tuscany.sca.runtime.RuntimeWire;
import org.osoa.sca.ServiceRuntimeException;

public class FaultTransformHelper {
    
    private Mediator mediator;
    
    public FaultTransformHelper(Mediator mediator) {
        this.mediator = mediator;
    }
    
    public Object transformFault(Object result, Operation sourceOperation, Operation targetOperation, RuntimeWire wire) {

            // FIXME: How to match fault data to a fault type for the
            // operation?

            // If the result is from an InvocationTargetException look at
            // the actual cause.
            if (result instanceof InvocationTargetException) {
                result = ((InvocationTargetException)result).getCause();
            }
            DataType targetDataType = null;
            for (DataType exType : targetOperation.getFaultTypes()) {
                if (((Class)exType.getPhysical()).isInstance(result)) {
                    if (result instanceof FaultException) {
                        DataType faultType = (DataType)exType.getLogical();
                        if (((FaultException)result).isMatchingType(faultType.getLogical())) {
                            targetDataType = exType;
                            break;
                        }
                    } else {
                        targetDataType = exType;
                        break;
                    }
                }
            }

            /*
            if (targetDataType == null) {
                // Not a business exception
                return resultMsg;
            }
            */

            DataType targetFaultType = getFaultType(targetDataType);
            if (targetFaultType == null) {
                // No matching fault type, it's a system exception
                Throwable cause = (Throwable) result;
                throw new ServiceRuntimeException(cause);
            }

            // FIXME: How to match a source fault type to a target fault
            // type?
            DataType sourceDataType = null;
            DataType sourceFaultType = null;
            for (DataType exType : sourceOperation.getFaultTypes()) {
                DataType faultType = getFaultType(exType);
                // Match by the QName (XSD element) of the fault type
                if (faultType != null && typesMatch(targetFaultType.getLogical(), faultType.getLogical())) {
                    sourceDataType = exType;
                    sourceFaultType = faultType;
                    break;
                }
            }

            if (sourceFaultType == null) {
                // No matching fault type, it's a system exception
                Throwable cause = (Throwable) result;
                throw new ServiceRuntimeException(cause);
            }

            Map<String, Object> metadata = new HashMap<String, Object>();
            metadata.put("source.operation", targetOperation);
            metadata.put("target.operation", sourceOperation);
            // When is the 'wire' used?
            if (wire != null) {
                metadata.put("wire", wire);
            }
            
            Object newResult =
                transformException(result, targetDataType, sourceDataType, targetFaultType, sourceFaultType, metadata);
            
            return newResult;

    }
    
    /**
     * @param source The source exception
     * @param sourceExType The data type for the source exception
     * @param targetExType The data type for the target exception
     * @param sourceType The fault type for the source
     * @param targetType The fault type for the target
     * @return
     */
    private Object transformException(Object source,
                                      DataType sourceExType,
                                      DataType targetExType,
                                      DataType sourceType,
                                      DataType targetType,
                                      Map<String, Object> metadata) {
        
        if (sourceType == targetType || (sourceType != null && sourceType.equals(targetType))) {
            return source;
        }

        DataType<DataType> eSourceDataType =
            new DataTypeImpl<DataType>("idl:fault", sourceExType.getPhysical(), sourceType);
        DataType<DataType> eTargetDataType =
            new DataTypeImpl<DataType>("idl:fault", targetExType.getPhysical(), targetType);

        return mediator.mediate(source, eSourceDataType, eTargetDataType, metadata);
    }
    
    private DataType getFaultType(DataType exceptionType) {
        return exceptionType == null ? null : (DataType)exceptionType.getLogical();
    }

    private boolean typesMatch(Object first, Object second) {
        if (first.equals(second)) {
            return true;
        }
        if (first instanceof XMLType && second instanceof XMLType) {
            XMLType t1 = (XMLType)first;
            XMLType t2 = (XMLType)second;
            // TUSCANY-2113, we should compare element names only
            return matches(t1.getElementName(), t2.getElementName());
        }
        return false;
    }
    
    /**
     * @param qn1
     * @param qn2
     */
    private boolean matches(QName qn1, QName qn2) {
        if (qn1 == qn2) {
            return true;
        }
        if (qn1 == null || qn2 == null) {
            return false;
        }
        String ns1 = qn1.getNamespaceURI();
        String ns2 = qn2.getNamespaceURI();
        String e1 = qn1.getLocalPart();
        String e2 = qn2.getLocalPart();
        if (e1.equals(e2) && (ns1.equals(ns2) || ns1.equals(ns2 + "/") || ns2.equals(ns1 + "/"))) {
            // Tolerating the trailing / which is required by JAX-WS java package --> xml ns mapping
            return true;
        }
        return false;
    }
}
