package screens.user_login;


import use_cases.user_login_use_case.UserLoginInputBoundary;
import use_cases.user_login_use_case.UserLoginRequestModel;
import use_cases.user_login_use_case.UserLoginResponseModel;

public class UserLoginController {
    final UserLoginInputBoundary userInput;

    public UserLoginController(UserLoginInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    UserLoginResponseModel create(String username, String password) {
        UserLoginRequestModel requestModel = new UserLoginRequestModel(username, password);

        return userInput.create(requestModel);
    }
}