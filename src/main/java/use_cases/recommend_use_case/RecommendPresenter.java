package use_cases.recommend_use_case;

import entities.User;

import java.util.List;

public class RecommendPresenter {


    /**
     * @param responseModel the recommend response model
     * @return a list of users to recommend
     */
    public List<User> prepareRecommendView(RecommendResponseModel responseModel) {
        return responseModel.getAllUsers();
    }


}
