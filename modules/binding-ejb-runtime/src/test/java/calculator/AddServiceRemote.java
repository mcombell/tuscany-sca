package calculator;

import javax.ejb.Remote;

@Remote
public interface AddServiceRemote {
    double add(double n1, double n2);
}
