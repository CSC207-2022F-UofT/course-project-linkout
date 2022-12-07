package use_cases.search_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class SearchController {

    final SearchInputBoundary searchInput;


    public SearchController(SearchInputBoundary input) {
        this.searchInput = input;
    }
    public SearchResponseModel searchSheet(String keywords, String userName) throws IOException, InvalidAttributeValueException {
        SearchRequestModel requestModel = new SearchRequestModel(keywords, userName);

        return searchInput.search(requestModel);
    }
}
