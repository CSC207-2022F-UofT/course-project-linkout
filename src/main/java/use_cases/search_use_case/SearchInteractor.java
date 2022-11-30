
package use_cases.search_use_case;

import entities.User;
import use_cases.search_use_case.SearchInputBoundary;
import use_cases.search_use_case.SearchRequestModel;
import use_cases.search_use_case.SearchResponseModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import java.util.List;

public class SearchInteractor {
    public class searchInteractor implements SearchInputBoundary {

        private final SearchDSGateway searchDsGateway;

        public searchInteractor(SearchDSGateway searchDsGateway) {
            this.searchDsGateway = searchDsGateway;
        }

        @Override
        public SearchResponseModel search(SearchRequestModel searchrequestModel) throws IOException, InvalidAttributeValueException {
            List<User> twentyMatchedUsers = searchDsGateway.searchSheet(searchrequestModel);

            // return a SearchResponseModel with a list of 20 matched users along with their corresponding profiles
            SearchResponseModel responseModel = new SearchResponseModel(twentyMatchedUsers);
            return responseModel;
        }


    }
}




