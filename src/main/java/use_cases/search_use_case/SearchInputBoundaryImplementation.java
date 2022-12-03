package use_cases.search_use_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public class SearchInputBoundaryImplementation implements SearchInputBoundary {
    public SearchResponseModel search(SearchRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String keywords = requestModel.getKeywords();
        String userName = requestModel.getUsername();
        SearchGateway searchGateway = new SearchGateway("/Users/xumichelle/Desktop/course-project-linkout");
        List<User> twentyMatchedUsers = searchGateway.searchSheet(keywords, userName);
        SearchResponseModel searchResponseModel = new SearchResponseModel(twentyMatchedUsers);
        return searchResponseModel;
    }
}
