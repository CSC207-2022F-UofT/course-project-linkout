package Gateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserUpgrade {
    boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException;
}
