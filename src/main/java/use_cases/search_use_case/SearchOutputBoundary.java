package use_cases.search_use_case;

import entities.User;

import java.util.List;

public interface SearchOutputBoundary {
    public ArrayList<User> SearchResultView(SearchResponseModel responseModel);
}