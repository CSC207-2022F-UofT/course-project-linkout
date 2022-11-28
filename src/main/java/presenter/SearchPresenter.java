package presenter;

import entities.User;
import use_cases.search_use_case.searchOutputBoundary;
import use_cases.search_use_case.SearchResponseModel;

import java.util.List;

public class SearchPresenter implements SearchoutputBoundary {


    @Override
    public ArrayList<User> SearchResultView(SearchResponseModel responseModel) {
        return responseModel.getAllMatchedUsers();
    }
}