package use_cases.recommend_use_case;

import entities.User;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecommendControllerTest {

    public void initializeDataset() throws IOException, InterruptedException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Reinitiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/likes.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/likes.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2).waitFor();
    }

    @Test
    void recommend() throws IOException, InterruptedException, InvalidAttributeValueException {

        initializeDataset();

        RecommendController recommendController = new RecommendController(new RecommendInteractor(new RecommendGateway(System.getProperty("user.dir"))));

        RecommendPresenter presenter = new RecommendPresenter(){
            @Override
            public List<User> PrepareRecommendView(RecommendResponseModel responseModel) {
                assertNotNull(responseModel.getAllUsers());
                assertTrue(responseModel.getAllUsers() instanceof List);
                return null;
            }
        };

        RecommendResponseModel recommended1 = recommendController.recommend("acc1");
        RecommendResponseModel recommended2 = recommendController.recommend("acc1", "acc47");

        presenter.PrepareRecommendView(recommended1);
        presenter.PrepareRecommendView(recommended2);

        initializeDataset();

    }


}