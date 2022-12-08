package use_cases.recommend_use_case;

import entities.User;

import java.util.List;

public class RecommendResponseModel {

    private final List<User> recommendedUsers;


    /**
     * @param lsusers the list of users to recommend
     */
    public RecommendResponseModel(List<User> lsusers){
        recommendedUsers = lsusers;
    }


    /**
     * @return the list of users to recommend
     */
    public List<User> getAllUsers(){
        return recommendedUsers;
    }
}
