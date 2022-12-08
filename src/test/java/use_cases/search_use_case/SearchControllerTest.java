package use_cases.search_use_case;

import entities.User;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchControllerTest {

    @Test
    public void searchSheet() throws IOException, InvalidAttributeValueException {
        SearchGateway db = new SearchGateway(System.getProperty("user.dir"));
        SearchInteractor searchInteractor = new SearchInteractor(db);
        SearchController searchController = new SearchController(searchInteractor);

        SearchPresenter presenter = new SearchPresenter() {
            @Override
            public List<User> SearchResultView(SearchResponseModel responseModel) {
                assertNotNull(responseModel.getTwentyMatchedUsers());
                assertTrue(responseModel.getTwentyMatchedUsers() != null);
                return null;
            }
        };

        // Test search
        //Input data
        String keyword = "22";
        String userName = "acc1";
        SearchResponseModel responseModel1 = searchController.searchSheet(keyword, userName);
        presenter.SearchResultView(responseModel1);
    }
}

