package use_cases.recommend_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface RecommendInputBoundary {


    RecommendResponseModel Recommend(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException;

}
