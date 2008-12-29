/*
 * Copyright (c) 2001-2008 Caucho Technology, Inc.  All rights reserved.
 *
 * The Apache Software License, Version 1.1
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Caucho Technology (http://www.caucho.com/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "Hessian", "Resin", and "Caucho" must not be used to
 *    endorse or promote products derived from this software without prior
 *    written permission. For written permission, please contact
 *    info@caucho.com.
 *
 * 5. Products derived from this software may not be called "Resin"
 *    nor may "Resin" appear in their names without prior written
 *    permission of Caucho Technology.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL CAUCHO TECHNOLOGY OR ITS CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Scott Ferguson
 */

package org.apache.tuscany.sca.binding.hessian.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tuscany.sca.interfacedef.Interface;
import org.apache.tuscany.sca.interfacedef.Operation;
import org.apache.tuscany.sca.interfacedef.java.JavaOperation;
import org.apache.tuscany.sca.invocation.Message;
import org.apache.tuscany.sca.invocation.MessageFactory;
import org.apache.tuscany.sca.runtime.RuntimeWire;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.SerializerFactory;
import com.caucho.services.server.ServiceContext;

/**
 * Servlet for serving Hessian services.
 */
public class HessianServiceListenerServlet extends GenericServlet {

    private static final Logger logger = Logger.getLogger(HessianServiceListenerServlet.class.getName());

    private Interface homeImpl;
    private RuntimeWire wire;
    private MessageFactory messageFactory;

    private SerializerFactory _serializerFactory;

    private Map<String, JavaOperation> _operationMap = new Hashtable<String, JavaOperation>();

    public HessianServiceListenerServlet(Interface homeImpl, RuntimeWire wire, MessageFactory messageFactory) {
        this.homeImpl = homeImpl;
        this.wire = wire;
        this.messageFactory = messageFactory;

        List<Operation> operations = homeImpl.getOperations();
        for (int i = 0; i < operations.size(); i++) {
            JavaOperation op = (JavaOperation)operations.get(i);
            Method method = op.getJavaMethod();
            if (_operationMap.get(method.getName()) == null)
                _operationMap.put(method.getName(), op);

            Class[] param = method.getParameterTypes();
            String mangledName = method.getName() + "__" + param.length;
            _operationMap.put(mangledName, op);
            _operationMap.put(mangleName(method, false), op);
        }
    }

    public String getServletInfo() {
        return "Tuscany Binding Hessian Servlet";
    }

    /**
     * Sets the serializer factory.
     */
    public void setSerializerFactory(SerializerFactory factory) {
        _serializerFactory = factory;
    }

    /**
     * Gets the serializer factory.
     */
    public SerializerFactory getSerializerFactory() {
        if (_serializerFactory == null)
            _serializerFactory = new SerializerFactory();

        return _serializerFactory;
    }

    /**
     * Sets the serializer send collection java type.
     */
    public void setSendCollectionType(boolean sendType) {
        getSerializerFactory().setSendCollectionType(sendType);
    }

    /**
     * Execute a request. The path-info of the request selects the bean. Once
     * the bean's selected, it will be applied.
     */
    public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        if (!req.getMethod().equals("POST")) {
            res.setStatus(500, "Hessian Requires POST");
            PrintWriter out = res.getWriter();
            res.setContentType("text/html");
            out.println("<h1>Hessian Requires POST</h1>");
            return;
        }

        String serviceId = req.getPathInfo();
        String objectId = req.getParameter("id");
        if (objectId == null)
            objectId = req.getParameter("ejbid");

        ServiceContext.begin(req, serviceId, objectId);

        try {
            InputStream is = request.getInputStream();
            OutputStream os = response.getOutputStream();

            Hessian2Input in = new Hessian2Input(is);
            AbstractHessianOutput out;

            SerializerFactory serializerFactory = getSerializerFactory();

            in.setSerializerFactory(serializerFactory);

            int code = in.read();

            if (code != 'c') {
                // XXX: deflate
                throw new IOException("expected 'c' in hessian input at " + code);
            }

            int major = in.read();
            int minor = in.read();

            if (major >= 2)
                out = new Hessian2Output(os);
            else
                out = new HessianOutput(os);

            out.setSerializerFactory(serializerFactory);

            invoke(in, out);

            out.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (ServletException e) {
            throw e;
        } catch (Throwable e) {
            throw new ServletException(e);
        } finally {
            ServiceContext.end();
        }
    }

    private String mangleName(Method method, boolean isFull) {
        StringBuffer sb = new StringBuffer();
        sb.append(method.getName());
        Class[] params = method.getParameterTypes();
        for (int i = 0; i < params.length; i++) {
            sb.append('_');
            sb.append(mangleClass(params[i], isFull));
        }
        return sb.toString();
    }

    private String mangleClass(Class cl, boolean isFull) {
        String name = cl.getName();
        if (name.equals("boolean") || name.equals("java.lang.Boolean"))
            return "boolean";
        else if (name.equals("int") || name.equals("java.lang.Integer")
            || name.equals("short")
            || name.equals("java.lang.Short")
            || name.equals("byte")
            || name.equals("java.lang.Byte"))
            return "int";
        else if (name.equals("long") || name.equals("java.lang.Long"))
            return "long";
        else if (name.equals("float") || name.equals("java.lang.Float")
            || name.equals("double")
            || name.equals("java.lang.Double"))
            return "double";
        else if (name.equals("java.lang.String") || name.equals("com.caucho.util.CharBuffer")
            || name.equals("char")
            || name.equals("java.lang.Character")
            || name.equals("java.io.Reader"))
            return "string";
        else if (name.equals("java.util.Date") || name.equals("com.caucho.util.QDate"))
            return "date";
        else if (InputStream.class.isAssignableFrom(cl) || name.equals("[B"))
            return "binary";
        else if (cl.isArray()) {
            return "[" + mangleClass(cl.getComponentType(), isFull);
        } else if (name.equals("org.w3c.dom.Node") || name.equals("org.w3c.dom.Element")
            || name.equals("org.w3c.dom.Document"))
            return "xml";
        else if (isFull)
            return name;
        else {
            int p = name.lastIndexOf('.');
            if (p > 0)
                return name.substring(p + 1);
            else
                return name;
        }
    }

    public void invoke(AbstractHessianInput in, AbstractHessianOutput out) throws Exception {
        ServiceContext context = ServiceContext.getContext();

        // backward compatibility for some frameworks that don't read
        // the call type first
        in.skipOptionalCall();

        String header;
        while ((header = in.readHeader()) != null) {
            Object value = in.readObject();
            context.addHeader(header, value);
        }

        String methodName = in.readMethod();
        Method method = null;
        JavaOperation operation = _operationMap.get(methodName);
        if (operation != null)
            method = operation.getJavaMethod();

        if (method != null) {
        } else if ("_hessian_getAttribute".equals(methodName)) {
            String attrName = in.readString();
            in.completeCall();
            String value = null;
            if ("java.api.class".equals(attrName))
                value = homeImpl.toString();
            else if ("java.home.class".equals(attrName))
                value = homeImpl.toString();
            else if ("java.object.class".equals(attrName))
                value = homeImpl.toString();
            out.startReply();
            out.writeObject(value);
            out.completeReply();
            return;
        } else if (method == null) {
            out.startReply();
            out.writeFault("NoSuchMethodException", "The service has no method named: " + in.getMethod(), null);
            out.completeReply();
            return;
        }

        Class[] args = method.getParameterTypes();
        Object[] values = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            values[i] = in.readObject(args[i]);
        }

        Object result = null;
        try {
            Message msg = messageFactory.createMessage();
            msg.setOperation(operation);
            msg.setBody(values);
            result = wire.invoke(operation, msg);
        } catch (Throwable e) {
            while (e instanceof InvocationTargetException)
                e = ((InvocationTargetException)e).getTargetException();
            out.startReply();
            out.writeFault("ServiceException", e.getMessage(), e);
            out.completeReply();
            return;
        }

        // The complete call needs to be after the invoke to handle a
        // trailing InputStream
        in.completeCall();
        out.startReply();
        out.writeObject(result);
        out.completeReply();
        out.close();
    }

}
