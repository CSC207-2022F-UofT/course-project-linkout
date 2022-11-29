package use_cases.recommend_use_case;

import static org.junit.jupiter.api.Assertions.*;

import use_cases.regular_user_register_use_case.UserGateway;
import entities.User;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;



public class RecommendInteractorTest {

    @Test
    public void RecommendTest() throws IOException, InvalidAttributeValueException {

        RecommendDsGateway recommendGateway = new RecommendGateway(System.getProperty("user.dir"));

        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));

        RecommendInteractor interactor = new RecommendInteractor(recommendGateway);
        RecommendPresenter presenter = new RecommendPresenter(){
            @Override
            public List<User> PrepareRecommendView(RecommendResponseModel responseModel) {
                assertNotNull(responseModel.getAllUsers());
                assertTrue(responseModel.getAllUsers() instanceof List);
                return null;
            }
        };

        // Test Popular
        User user1 = userGateway.findUser("acc0");
        RecommendRequestModel requestModel1 = new RecommendRequestModel(user1);
        RecommendResponseModel responseModel1 = interactor.Recommend(requestModel1);

        presenter.PrepareRecommendView(responseModel1);

        // Test Recommend
        User user2 = userGateway.findUser("acc1");
        RecommendRequestModel requestModel2 = new RecommendRequestModel(user2);
        RecommendResponseModel responseModel2 = interactor.Recommend(requestModel2);

        presenter.PrepareRecommendView(responseModel2);

        // Test Similar
        RecommendRequestModel requestModel3 = new RecommendRequestModel(user2, "acc47");
        RecommendResponseModel responseModel3 = interactor.Recommend(requestModel3);

        presenter.PrepareRecommendView(responseModel3);



    }


}
