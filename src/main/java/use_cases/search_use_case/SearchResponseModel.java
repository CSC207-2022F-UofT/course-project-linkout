package use_cases.search_use_case;

import entities.User;

import java.util.List;


public class SearchResponseModel {

    private ArrayList<User> twentyMatchedUsers;

    /**
     * Create a new SearchResponseModel with an ArrayList of 20 users that matched with the interested features 
     *
     * @param twentyMatchedUsers an ArrayList of users that matched with the interested features
     */

    public SearchResponseModel(ArrayList<User> twentyMatchedUsers){
        this.twentyMatchedUsers = twentyMatchedUsers;
    }

    public ArrayList<User> getTwentyMatchedUsers(){
        return this.twentyMatchedUsers;
    }
}