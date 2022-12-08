package use_cases.user_manager_user_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserDsGateway {

    public User findUser(String name) throws IOException, InvalidAttributeValueException;

    public boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException;
}
