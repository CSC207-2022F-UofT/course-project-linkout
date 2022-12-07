package use_cases.search_use_case;

import entities.User;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SearchTest {

    @Test
    public void SearchTest() throws IOException, InvalidAttributeValueException {

        SearchGateway searchGateway = new SearchGateway(System.getProperty("user.dir"));

        SearchPresenter presenter = new SearchPresenter(){
            @Override
            public List<User> SearchResultView(SearchResponseModel responseModel){
                assertNotNull(responseModel.getTwentyMatchedUsers());
                assertTrue(responseModel.getTwentyMatchedUsers() instanceof List);
                return null;
            }
        };

        // Test searchSheet
            //Input data
            String keywords = "22,m";
            String userName = "acc1";
            List<User> matchedUser = searchGateway.searchSheet(keywords, userName);
            SearchResponseModel responseModel1 = new SearchResponseModel(matchedUser);
            presenter.SearchResultView(responseModel1);
    }


}