package use_cases.recommend_use_case;

import entities.User;

import java.util.List;

public class RecommendRequestModel {

    private String username;
    private List<String> liked;

    private String similarTo = null;

    public RecommendRequestModel(User user){
        username = user.getAccountName();
        liked = user.showLiked();
    }

    public RecommendRequestModel(User user, String userviewed){
        username = user.getAccountName();
        liked = user.showLiked();
        similarTo = userviewed;
    }

    public String getUsername(){
        return username;
    }

    public String getSimilarTo(){
        return similarTo;
    }

    public List<String> getLiked(){
        return liked;
    }

    public boolean hasLiked(){
        return (liked.size() != 0);
    }

}
