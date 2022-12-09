package use_cases.recommend_use_case;

import static org.junit.jupiter.api.Assertions.*;

import entities.User;
import org.junit.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;



public class RecommendInteractorTest {

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
    public void RecommendTest() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();

        RecommendDsGateway recommendGateway = new RecommendGateway(System.getProperty("user.dir"));

        RecommendInteractor interactor = new RecommendInteractor(recommendGateway);
        RecommendPresenter presenter = new RecommendPresenter(){
            @Override
            public List<User> prepareRecommendView(RecommendResponseModel responseModel) {
                assertNotNull(responseModel.getAllUsers());
                assertTrue(responseModel.getAllUsers() instanceof List);
                return null;
            }
        };

        // Test Popular
        RecommendRequestModel requestModel1 = new RecommendRequestModel("acc0");
        RecommendResponseModel responseModel1 = interactor.Recommend(requestModel1);

        presenter.prepareRecommendView(responseModel1);

        // Test Recommend
        RecommendRequestModel requestModel2 = new RecommendRequestModel("acc1");
        RecommendResponseModel responseModel2 = interactor.Recommend(requestModel2);

        presenter.prepareRecommendView(responseModel2);

        // Test Similar
        RecommendRequestModel requestModel3 = new RecommendRequestModel("acc1", "acc10");
        RecommendResponseModel responseModel3 = interactor.Recommend(requestModel3);

        presenter.prepareRecommendView(responseModel3);
        initializeDataset();


    }


}
