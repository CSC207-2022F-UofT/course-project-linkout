package use_cases.RecommendUseCase;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface RecommendInputBoundary {


    RecommendResponseModel Recommend(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException;

}
