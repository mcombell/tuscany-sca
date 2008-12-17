package calculator;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of the Add service
 */
public class AddServiceImpl implements AddService {

    public double add(double n1, double n2) {
        Logger logger = Logger.getLogger("calculator");
        logger.log(Level.FINEST, "Adding " + n1 + " and " + n2);
        return n1 + n2;
    }

}
