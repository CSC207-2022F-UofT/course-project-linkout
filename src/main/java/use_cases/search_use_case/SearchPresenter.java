package use_cases.search_use_case;

import entities.User;
import use_cases.search_use_case.SearchOutputBoundary;
import use_cases.search_use_case.SearchResponseModel;

import java.util.List;

public class SearchPresenter implements SearchOutputBoundary {

    /**
     * this method presents an ArrayList that contains 20 users that matched with the keywords entered 

     * @return List<User>
     */

    @Override
    public List<User> SearchResultView(SearchResponseModel responseModel) {
        return responseModel.getTwentyMatchedUsers();
    }
}