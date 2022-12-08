package use_cases.search_use_case;

import entities.User;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchInteractorTest {
    @Test
    public void searchSheet() throws IOException, InvalidAttributeValueException {
        SearchPresenter presenter = new SearchPresenter() {
            @Override
            public List<User> SearchResultView(SearchResponseModel responseModel) {
                assertNotNull(responseModel.getTwentyMatchedUsers());
                assertTrue(responseModel.getTwentyMatchedUsers() instanceof List);
                return null;
            }
        };
        //Input data
        String keyword = "22";
        String userName = "acc1";
        SearchDSGateway searchDSGateway = new SearchGateway(System.getProperty("user.dir"));
        SearchInteractor searchInteractor = new SearchInteractor(searchDSGateway);
        SearchRequestModel requestModel = new SearchRequestModel(keyword, userName);
        SearchResponseModel responseModel = searchInteractor.searchSheet(requestModel);
        List<User> twentyMatchedUsers = searchDSGateway.searchSheet(keyword,userName);
        presenter.SearchResultView(responseModel);
    }
}
