package use_cases.recommend_use_case;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendRequestModel {

    private String username;

    private String similarTo = null;

    /**
     * @param usrname the user need to recommend
     */
    public RecommendRequestModel(String usrname){
        username = usrname;
    }


    /**
     * @param usrname the user need to recommend
     * @param userviewed the target user for similarity
     */
    public RecommendRequestModel(String usrname, String userviewed){
        username = usrname;
        similarTo = userviewed;
    }

    /**
     * @return the user need to recommend
     */
    public String getUsername(){
        return username;
    }

    /**
     * @return the target user for similarity
     */
    public String getSimilarTo(){
        return similarTo;
    }


}
