package calculator.android;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.net.URL;
import java.security.Permission;

import org.apache.tuscany.sca.contribution.Contribution;
import org.apache.tuscany.sca.contribution.service.ContributionService;
import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.apache.tuscany.sca.host.embedded.impl.EmbeddedSCADomain;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import calculator.CalculatorService;

public class CalculatorClient extends Activity {
	private EmbeddedSCADomain domain;
	private CalculatorService calculatorService;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String calculationResult = "Error !";
        try {
        	System.setSecurityManager(new SecurityManager() {
        		@Override
        		public void checkPermission(Permission permission) {
        			
        		}
        		
        		@Override
        		public void checkAccept(String host, int port) {
        		
        		}
        		
        		@Override
        		public void checkAccess(Thread thread) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkAccess(ThreadGroup group) {
        		
        		}
        		
        		@Override
        		public void checkAwtEventQueueAccess() {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkConnect(String host, int port) {
        		
        		}
        		@Override
        		public void checkConnect(String host, int port, Object context) {
        		
        		}
        		@Override
        		public void checkCreateClassLoader() {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkDelete(String file) {
        		
        		}
        		@Override
        		public void checkExec(String cmd) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkExit(int status) {
        		
        		}
        		@Override
        		public void checkLink(String libName) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkListen(int port) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkMemberAccess(Class<?> cls, int type) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkMulticast(InetAddress maddr) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkMulticast(InetAddress maddr, byte ttl) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkPackageAccess(String packageName) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkPackageDefinition(String packageName) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkPermission(Permission permission,
        				Object context) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkPrintJobAccess() {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkPropertiesAccess() {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkPropertyAccess(String key) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkRead(FileDescriptor fd) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkRead(String file) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkRead(String file, Object context) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkSecurityAccess(String target) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkSetFactory() {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkSystemClipboardAccess() {
        			// TODO Auto-generated method stub
        		
        		}
        		
        		@Override
        		public void checkWrite(FileDescriptor fd) {
        			// TODO Auto-generated method stub
        		
        		}
        		@Override
        		public void checkWrite(String file) {
        			// TODO Auto-generated method stub
        		
        		}
        		
        	});
        	
        	Thread.currentThread().setContextClassLoader(getClassLoader());
        	
        	SCADomain scaDomain = SCADomain.newInstance("Calculator.composite");
            
            CalculatorService calculatorService = 
                scaDomain.getService(CalculatorService.class, "CalculatorServiceComponent");

            // Calculate
            System.out.println("3 + 2=" + calculatorService.add(3, 2));
            System.out.println("3 - 2=" + calculatorService.subtract(3, 2));
            System.out.println("3 * 2=" + calculatorService.multiply(3, 2));
            System.out.println("3 / 2=" + calculatorService.divide(3, 2));

            scaDomain.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        
        TextView tv = new TextView(this);
        tv.setText("3 + 2 =" + calculationResult);
        setContentView(tv);
        
    }
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		try {
			//scaDomain.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
    @Override
	public void onContentChanged() {
		super.onContentChanged();
		/*
        // Calculate
        System.out.println("3 + 2=" + calculatorService.add(3, 2));
        System.out.println("3 - 2=" + calculatorService.subtract(3, 2));
        System.out.println("3 * 2=" + calculatorService.multiply(3, 2));
        System.out.println("3 / 2=" + calculatorService.divide(3, 2));
        */

	}
	
	
}