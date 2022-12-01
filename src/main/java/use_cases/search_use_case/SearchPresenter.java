package use_cases.search_use_case;

import entities.User;
import use_cases.search_use_case.searchOutputBoundary;
import use_cases.search_use_case.SearchResponseModel;

import java.util.List;

public class SearchPresenter implements SearchoutputBoundary {

    /**
     * this method presents an ArrayList that contains 20 users that matched with the keywords entered 

     * @return ArrayList<User>  
     */

    @Override
    public ArrayList<User> SearchResultView(SearchResponseModel responseModel) {
        return responseModel.getTwentyMatchedUsers();
    }
}