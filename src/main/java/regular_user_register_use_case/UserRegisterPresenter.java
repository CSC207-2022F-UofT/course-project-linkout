package regular_user_register_use_case;


public interface UserRegisterPresenter {
    UserRegisterResponseModel prepareSuccessView(UserRegisterResponseModel user);

    UserRegisterResponseModel prepareFailView(String error);
}