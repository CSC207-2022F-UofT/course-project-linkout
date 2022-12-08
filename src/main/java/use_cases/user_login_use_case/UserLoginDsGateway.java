package use_cases.user_login_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserLoginDsGateway {
    boolean MatchingNameAndPassword(String accountName, String Password) throws IOException, InvalidAttributeValueException;
    boolean NotExist(String accountName) throws IOException, InvalidAttributeValueException;
}
