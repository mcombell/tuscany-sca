package calculator;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of the subtract service.
 */
public class SubtractServiceImpl implements SubtractService {

    public double subtract(double n1, double n2) {
        Logger logger = Logger.getLogger("calculator");
        logger.log(Level.FINEST, "Subtracting " + n1 + " from " + n2);
        return n1 - n2;
    }

}
