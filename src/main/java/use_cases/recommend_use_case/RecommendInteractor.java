package use_cases.recommend_use_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RecommendInteractor implements RecommendInputBoundary{


    private final RecommendDsGateway db;


    /**
     * @param database the current working directory
     */
    public RecommendInteractor(RecommendDsGateway database){
        this.db = database;
    }

    private void updatePopular(String username) throws IOException {

        String[] command = {"bash", "-c", "source "+String.format("%s/src/main/recommendmodel/venv/bin/activate; ", db.getWorkingDir())+
                "python "+String.format("%s/src/main/recommendmodel/recommend.py", db.getWorkingDir())+
                String.format(" %s/src/main/data", db.getWorkingDir())+" popular "+String.format("--username=%s", username)};

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


    /**
     * @param username the user need to recommend
     * @param userviewed the target user for similarity
     * @throws IOException Can't find database
     */
    private void updateSimilar(String username, String userviewed) throws IOException {

        String[] command = {"bash", "-c", "source "+String.format("%s/src/main/recommendmodel/venv/bin/activate; ", db.getWorkingDir())+
                "python "+String.format("%s/src/main/recommendmodel/recommend.py", db.getWorkingDir())+
                String.format(" %s/src/main/data", db.getWorkingDir())+" similar "+String.format("--username=%s", username)+
                String.format(" --userviewed=%s", userviewed)};

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

    /**
     * @param username the user need to recommend
     * @throws IOException Can't find database
     */
    private void updateRecommend(String username) throws IOException {

        String[] command = {"bash", "-c", "source "+String.format("%s/src/main/recommendmodel/venv/bin/activate; ", db.getWorkingDir())+
                "python "+String.format("%s/src/main/recommendmodel/recommend.py", db.getWorkingDir())+
                String.format(" %s/src/main/data", db.getWorkingDir())+" recommend "+String.format("--username=%s", username)};

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


    /**
     * @param requestModel the recommend request model
     * @return the recommend response model
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    private RecommendResponseModel Popular(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String username = requestModel.getUsername();
        updatePopular(username);
        List<User> popular = db.loadAllUser("popular");
        return new RecommendResponseModel(popular);
    }

    /**
     * @param requestModel the recommend request model
     * @return the recommend response model
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    private RecommendResponseModel Similar(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String username = requestModel.getUsername();
        updateSimilar(username, requestModel.getSimilarTo());
        List<User> recommended = db.loadAllUser("similar");
        return new RecommendResponseModel(recommended);
    }


    /**
     * @param requestModel the recommend request model
     * @return the recommend response model
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    private RecommendResponseModel RecommendUsers(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        String username = requestModel.getUsername();
        updateRecommend(username);
        List<User> recommend = db.loadAllUser("recommend");
        return new RecommendResponseModel(recommend);
    }

    /**
     * @param requestModel the recommend request model
     * @return the recommend response model
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    @Override
    public RecommendResponseModel Recommend(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        RecommendResponseModel responseModel;
        if (requestModel.getSimilarTo() != null){
            responseModel = Similar(requestModel);
        } else if (db.hasLiked(requestModel.getUsername())){
            responseModel = RecommendUsers(requestModel);
        } else {
            responseModel = Popular(requestModel);
        }
        db.saveSeen(requestModel.getUsername(), responseModel.getAllUsers());
        return responseModel;
    }

}
