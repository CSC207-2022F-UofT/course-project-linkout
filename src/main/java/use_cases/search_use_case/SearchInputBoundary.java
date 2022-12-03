
package use_cases.search_use_case;
import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public interface SearchInputBoundary {
    SearchResponseModel search(SearchRequestModel requestModel) throws IOException, InvalidAttributeValueException;
}

