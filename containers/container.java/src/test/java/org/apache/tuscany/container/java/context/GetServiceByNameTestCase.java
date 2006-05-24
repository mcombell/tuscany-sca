package org.apache.tuscany.container.java.context;

import java.util.Collections;

import org.apache.tuscany.container.java.mock.MockContextFactory;
import org.apache.tuscany.container.java.mock.components.Target;
import org.apache.tuscany.container.java.mock.components.TargetImpl;
import org.apache.tuscany.core.context.scope.ModuleScopeContext;
import org.apache.tuscany.spi.model.Scope;
import org.apache.tuscany.spi.wire.TargetWire;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;

/**
 * @version $$Rev$$ $$Date$$
 */
public class GetServiceByNameTestCase extends MockObjectTestCase {

    public void testServiceLocate() throws Exception {
        ModuleScopeContext scope = new ModuleScopeContext(null);
        scope.start();
        final JavaAtomicContext<?> context =
                MockContextFactory.createJavaAtomicContext("target", scope, TargetImpl.class, Target.class, Scope.MODULE);

        Mock mock = mock(TargetWire.class);
        mock.stubs().method("getServiceName").will(returnValue("Target"));
        mock.stubs().method("getInvocationChains").will(returnValue(Collections.emptyMap()));

        TargetWire<Target> wire = (TargetWire<Target>) mock.proxy();
        context.addTargetWire(wire);
        context.prepare();
        context.start();

        mock.expects(once()).method("getTargetService").will(returnValue(context.getTargetInstance()));

        Target target = (Target) context.getService("Target");
        target.setString("foo");
        assertEquals("foo",target.getString());
    }

    

}
