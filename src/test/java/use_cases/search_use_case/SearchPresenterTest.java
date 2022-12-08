package use_cases.search_use_case;

import entities.User;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPresenterTest {

    @Test
    public void SearchResultView() throws IOException, InvalidAttributeValueException {

        SearchGateway searchGateway = new SearchGateway(System.getProperty("user.dir"));

        List<User> matched = searchGateway.searchSheet("acc1", "acc2");
        SearchResponseModel responseModel = new SearchResponseModel(matched);

        SearchPresenter presenter = new SearchPresenter();
        assertEquals(presenter.SearchResultView(responseModel).get(0).getAccountName(), "acc1");
    }
}