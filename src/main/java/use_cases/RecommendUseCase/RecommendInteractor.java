package use_cases.RecommendUseCase;

import entities.User;
import entities.UserFactory;

import javax.management.InvalidAttributeValueException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RecommendInteractor implements RecommendInputBoundary{


    private RecommendDsGateway db;


    public RecommendInteractor(String workingdir, RecommendDsGateway database){
        this.db = database;
    }

    private void UpdatePopular(String username) throws IOException {
        String[] command = {"python", String.format("%s/src/main/recommendmodel/recommend.py", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir()), "popular", String.format("--username=%s", username)};

        String s;
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);

        System.out.println("Here is the standard output of the command:\n");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }


    private void UpdateSimilar(String username, String userviewed) throws IOException {
        String[] command = {"python", String.format("%s/src/main/recommendmodel/recommend.py", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir()), "similar", String.format("--username=%s", username),
                String.format("--userviewed=%s", userviewed)};

        String s;
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);

        System.out.println("Here is the standard output of the command:\n");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }

    private void UpdateRecommend(String username) throws IOException {
        String[] command = {"python", String.format("%s/src/main/recommendmodel/recommend.py", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir()), "recommend", String.format("--username=%s", username)};

        String s;
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);

        System.out.println("Here is the standard output of the command:\n");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }


    public RecommendResponseModel Popular(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String username = requestModel.getUsername();
        UpdatePopular(username);
        List<User> popular = db.LoadAllUser("popular");
        return new RecommendResponseModel(popular);
    }


    public RecommendResponseModel Similar(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String username = requestModel.getUsername();
        UpdateSimilar(username, requestModel.getSimilarTo());
        List<User> recommended = db.LoadAllUser("similar");
        return new RecommendResponseModel(recommended);
    }



    public RecommendResponseModel RecommendUsers(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String username = requestModel.getUsername();
        UpdateRecommend(username);
        List<User> recommend = db.LoadAllUser("recommend");
        RecommendResponseModel recommendInfo = new RecommendResponseModel(recommend);
        return recommendInfo;
    }

    @Override
    public RecommendResponseModel Recommend(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        if (requestModel.getSimilarTo() != null){
            return Similar(requestModel);
        } else if (requestModel.hasLiked()){
            return RecommendUsers(requestModel);
        } else {
            return Popular(requestModel);
        }
    }

}
