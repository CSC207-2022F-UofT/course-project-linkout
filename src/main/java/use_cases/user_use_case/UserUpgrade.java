package use_cases.user_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserUpgrade {
    boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException;
}
