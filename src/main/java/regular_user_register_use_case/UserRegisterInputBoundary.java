package user_register_use_case;

public interface UserRegisterInputBoundary {
    UserRegisterResponseModel create(UserRegisterRequestModel requestModel);
}
