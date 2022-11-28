package use_cases.RecommendUseCase;

import entities.User;

import java.util.List;

public interface RecommendOutputBoundary {

    public List<User> PrepareRecommendView(RecommendResponseModel responseModel);


}
