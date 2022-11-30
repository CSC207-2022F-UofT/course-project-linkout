package use_cases.search_use_case;

import entities.User;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public interface SearchDSGateway {
    List<User> searchSheet(SearchRequestModel searchrequestModel) throws IOException, InvalidAttributeValueException;

    void SaveSeen(String username, String userviewed) throws IOException, InvalidAttributeValueException;

}
