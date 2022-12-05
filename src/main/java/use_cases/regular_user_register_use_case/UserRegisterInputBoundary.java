package use_cases.regular_user_register_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserRegisterInputBoundary {
    UserRegisterResponseModel create(UserRegisterRequestModel requestModel) throws IOException, InvalidAttributeValueException;
}
