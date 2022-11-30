package use_cases.search_use_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class SearchController {

    final SearchInputBoundary searchInput;

    public SearchController(SearchInputBoundary input) {
        this.searchInput = input;
    }
    public SearchResponseModel search(String keywords, String username) throws IOException, InvalidAttributeValueException {
        SearchRequestModel requestModel = new SearchRequestModel(keywords, username);

        return searchInput.search(requestModel);
    }

}
