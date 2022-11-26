package regular_user_register_use_case;

public interface UserRegisterDsGateway {
    boolean existsByName(String identifier);

    void save(UserRegisterDsRequestModel requestModel);
}
