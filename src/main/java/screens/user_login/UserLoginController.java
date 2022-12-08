package screens.user_login;


import use_cases.user_login_use_case.UserLoginInputBoundary;
import use_cases.user_login_use_case.UserLoginRequestModel;
import use_cases.user_login_use_case.UserLoginResponseModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class UserLoginController {
    final UserLoginInputBoundary userInput;

    public UserLoginController(UserLoginInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    UserLoginResponseModel create(String username, String password) throws IOException, InvalidAttributeValueException {
        UserLoginRequestModel requestModel = new UserLoginRequestModel(username, password);

        return userInput.create(requestModel);
    }
}