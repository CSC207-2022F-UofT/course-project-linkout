package use_cases.search_use_case;

import entities.User;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public interface SearchDSGateway {
    List<User> searchSheet(String keywords, String username) throws IOException, InvalidAttributeValueException;

    String getWorkingDir();

    void SaveSeen(String username, List<User> usersViewed) throws IOException, InvalidAttributeValueException;

}