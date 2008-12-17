package calculator;

import org.apache.tuscany.sca.host.embedded.SCADomain;



/**
 * This client program shows how to create an SCA runtime, start it,
 * and locate and invoke a SCA component
 */
public class CalculatorClient {
    public static void main(String[] args) throws Exception {

        
        SCADomain scaDomain = SCADomain.newInstance("Calculator.composite");
        
        CalculatorService calculatorService = 
            scaDomain.getService(CalculatorService.class, "CalculatorServiceComponent");

        // Calculate
        System.out.println("3 + 2=" + calculatorService.add(3, 2));
        System.out.println("3 - 2=" + calculatorService.subtract(3, 2));
        System.out.println("3 * 2=" + calculatorService.multiply(3, 2));
        System.out.println("3 / 2=" + calculatorService.divide(3, 2));

        scaDomain.close();
        
    }

}
