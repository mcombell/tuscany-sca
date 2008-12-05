package callbacks;

import org.apache.tuscany.sca.node.SCAClient;
import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.SCANodeFactory;

public class CallbackJMSClient {
	
	public static void main(String[] args) throws Exception {

        SCANodeFactory factory = SCANodeFactory.newInstance();
        SCANode node = factory.createSCANodeFromClassLoader("callbacks.composite", CallbackJMSClient.class.getClassLoader());
        node.start();
        
        OrderServiceClient orderServiceClient = ((SCAClient)node).getService(OrderServiceClient.class, "ClientComponent");
        
        // do something
        orderServiceClient.doSomeOrdering();
        
        // wait to give the service time to respond
        Thread.sleep(500);
        
        node.stop();
    }

}
