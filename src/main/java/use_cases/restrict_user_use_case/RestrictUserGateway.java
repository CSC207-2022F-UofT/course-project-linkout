package use_cases.restrict_user_use_case;

import entities.*;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface RestrictUserGateway {
    User findUser(String userID) throws IOException, InvalidAttributeValueException;
}