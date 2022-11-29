package use_cases.RecommendUseCase;

import entities.User;

import java.util.List;

public class RecommendResponseModel {

    private List<User> recommendedUsers;


    public RecommendResponseModel(List<User> lsusers){
        recommendedUsers = lsusers;
    }

    public List<User> getAllUsers(){
        return recommendedUsers;
    }
}
