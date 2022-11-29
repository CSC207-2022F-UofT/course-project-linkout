package use_cases.regular_user_register_use_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserRegisterDsGateway {
    boolean existsByName(String identifier) throws IOException, InvalidAttributeValueException;

    void saveUser(UserRegisterDsRequestModel requestModel) throws IOException, InvalidAttributeValueException;
}
