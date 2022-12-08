package use_cases.recommend_use_case;

import entities.User;
import org.junit.jupiter.api.Test;
import use_cases.regular_user_register_use_case.UserGateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecommendPresenterTest {

    @Test
    void prepareRecommendView() throws IOException, InvalidAttributeValueException {

        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));

        List<User> userList = new ArrayList<>();
        User user = userGateway.findUser("acc1");
        userList.add(user);
        RecommendResponseModel responseModel = new RecommendResponseModel(userList);

        RecommendPresenter presenter = new RecommendPresenter();
        assertEquals(presenter.PrepareRecommendView(responseModel).get(0).getAccountName(), "acc1");

    }
}