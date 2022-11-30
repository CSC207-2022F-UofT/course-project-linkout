package use_cases.search_use_case;

import entities.User;

import java.util.List;


public class SearchResponseModel {

    private List<User> twentyMatchedUsers;

    /**
     * Create a new SearchResponseModel with a List of 20 users that matched with the interested features
     *
     * @param twentyMatchedUsers a List of users that matched with the interested features
     */

    public SearchResponseModel(List<User> twentyMatchedUsers){
        this.twentyMatchedUsers = twentyMatchedUsers;
    }

    public List<User> getTwentyMatchedUsers(){
        return this.twentyMatchedUsers;
    }
}