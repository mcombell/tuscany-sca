package calculator.android;

import java.net.URL;

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