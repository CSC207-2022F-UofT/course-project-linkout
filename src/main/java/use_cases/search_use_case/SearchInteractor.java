
package use_cases.search_use_case;

import entities.User;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import java.util.List;
public class SearchInteractor implements SearchInputBoundary {
    private final SearchDSGateway searchDSGateway;
    public SearchInteractor(SearchDSGateway searchDSGateway) {
        this.searchDSGateway = searchDSGateway;
    }
    @Override
    public SearchResponseModel searchSheet(SearchRequestModel searchrequestModel) throws IOException, InvalidAttributeValueException {
        String keywords = searchrequestModel.getKeywords();
        String username = searchrequestModel.getUsername();
        List<User> twentyMatchedUsers = searchDSGateway.searchSheet(keywords,username);
        // return a SearchResponseModel with a list of 20 matched users along with their corresponding profiles
        SearchResponseModel responseModel = new SearchResponseModel(twentyMatchedUsers);
        return responseModel;
    }
}