package calculator;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of the Multiply service.
 */
public class MultiplyServiceImpl implements MultiplyService {

    public double multiply(double n1, double n2) {
        Logger logger = Logger.getLogger("calculator");
        logger.log(Level.FINEST, "Multiplying " + n1 + " with " + n2);
        return n1 * n2;
    }

}
