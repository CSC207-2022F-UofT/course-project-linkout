package use_cases.search_use_case;

import entities.User;

import java.util.List;

/**
     * Create a new SearchResponseModel with an ArrayList of users that matched with the interested features 
     *
     * @param MatchedUser an ArrayList of users that matched with the interested features
     */

public class SearchResponseModel {
    

    private ArrayList<User> MatchedUser;


    public SearchResponseModel(ArrayList<User> matchedusers){
        this.MatchedUser = matchedusers;
    }

    public ArrayList<User> getAllMatchedUsers(){
        return MatchedUsers;
    }
}