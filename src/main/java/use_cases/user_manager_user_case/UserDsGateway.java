package use_cases.user_manager_user_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserDsGateway {

    User findUser(String name) throws IOException, InvalidAttributeValueException;

    boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException;
}
