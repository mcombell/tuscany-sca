package calculator;


//import org.osoa.sca.annotations.Reference;


/**
 * An implementation of the Calculator service.
 */
public class CalculatorServiceImpl implements CalculatorService {

    private AddService addService;
    private SubtractService subtractService;
    private MultiplyService multiplyService;
    private DivideService divideService;


    //@Reference
    public void setAddService(AddService addService) {
        this.addService = addService;
    }

    //@Reference
    public void setSubtractService(SubtractService subtractService) {
        this.subtractService = subtractService;
    }

    //@Reference
    public void setDivideService(DivideService divideService) {
        this.divideService = divideService;
    }

    //@Reference
    public void setMultiplyService(MultiplyService multiplyService) {
        this.multiplyService = multiplyService;
    }

    public double add(double n1, double n2) {
        return addService.add(n1, n2);
    }

    public double subtract(double n1, double n2) {
        return subtractService.subtract(n1, n2);
    }

    public double multiply(double n1, double n2) {
        return multiplyService.multiply(n1, n2);
    }

    public double divide(double n1, double n2) {
        return divideService.divide(n1, n2);
    }

}
