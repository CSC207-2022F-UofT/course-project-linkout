package screens.user_login;

import use_cases.user_login_use_case.UserLoginPresenter;
import use_cases.user_login_use_case.UserLoginResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserLoginResponseFormatter implements UserLoginPresenter {
    @Override
    public UserLoginResponseModel LoginFailView(String error) {
        throw new UserLoginFailed(error);
    }

    @Override
    public UserLoginResponseModel LoginSuccessView(UserLoginResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
}}
