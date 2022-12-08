package use_cases.user_login_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserLoginInputBoundary {
    UserLoginResponseModel create(UserLoginRequestModel requestModel) throws IOException, InvalidAttributeValueException;
}
