package use_cases.user_login_use_case;

public interface UserLoginInputBoundary {
    UserLoginResponseModel create(UserLoginRequestModel requestModel);
}
