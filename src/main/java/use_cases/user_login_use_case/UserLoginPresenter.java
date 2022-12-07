package use_cases.user_login_use_case;

public interface UserLoginPresenter {
    UserLoginResponseModel LoginFailView(String error);

    UserLoginResponseModel LoginSuccessView(UserLoginResponseModel accountResponseModel);
}
