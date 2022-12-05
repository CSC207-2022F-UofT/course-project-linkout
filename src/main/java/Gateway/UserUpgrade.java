package Gateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserUpgrade {
    public boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException;
}
