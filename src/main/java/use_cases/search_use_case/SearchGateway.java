package use_cases.search_use_case;

import entities.User;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface SearchDSGateway {

    HSSFSheet getSheetOfAllUsers() throws IOException, InvalidAttributeValueException;

}
    