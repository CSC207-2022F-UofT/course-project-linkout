package use_cases.user_action_use_case;
//import entities.DatabaseConnect;

import entities.User;

import java.io.IOException;

//use case layer
public class UserActInteractor implements UserActInputBoundary{

    private final UserActDsGateway userActDsGateway;
    private final UserActPresenterInterface presenterInterface;

    public UserActInteractor(UserActDsGateway userActDsGateway, UserActPresenterInterface presenterInterface) {
        this.userActDsGateway = userActDsGateway;
        this.presenterInterface = presenterInterface;
    }


    @Override
    public String like(UserActInputData inputData) {
        String accName = inputData.getAccName();
        String targetName = inputData.getTargetName();
        //if target user already liked
        try {
            if (userActDsGateway.isLiked(accName, targetName)){
                return presenterInterface.prepareFailView("User already liked!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // save like information
        User actioner = userActDsGateway.findUser(accName);
        actioner.like(targetName);
        try {
            userActDsGateway.setLike(accName, targetName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // check if matched
        try {
            if (userActDsGateway.isLiked(targetName, accName) ){
                return presenterInterface.prepareMatchingView(targetName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //present successfully liked target user
        return presenterInterface.prepareSuccessView(targetName);
    }



}
