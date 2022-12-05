package use_cases.recommend_use_case;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class RecommendRequestModel {

    private String username;

    private String similarTo = null;

    public RecommendRequestModel(String usrname){
        username = usrname;
    }

    public RecommendRequestModel(String usrname, String userviewed){
        username = usrname;
        similarTo = userviewed;
    }

    public String getUsername(){
        return username;
    }

    public String getSimilarTo(){
        return similarTo;
    }


}
