package use_cases.user_action_use_case;


import java.io.IOException;

//use case layer
public class UserActInteractor implements UserActInputBoundary{

    private final UserActDsGateway userActDsGateway;
    private final UserActPresenterInterface presenterInterface;

    public UserActInteractor(UserActDsGateway userActDsGateway, UserActPresenterInterface presenterInterface) {
        this.userActDsGateway = userActDsGateway;
        this.presenterInterface = presenterInterface;
    }


    /**
     * @param inputData contains the account names of an actioner and a target
     * @return return a String that is a message of the result.
     *
     */
    @Override
    public String like(UserActInputData inputData) throws IOException {
        String actionerName = inputData.getAccName();
        String targetName = inputData.getTargetName();
        // check if matched
        if (userActDsGateway.isLiked(targetName, actionerName) ){
            // save like information
            userActDsGateway.setLike(actionerName, targetName);
            
            return presenterInterface.prepareMatchingView(targetName);
        }
        //if target user already liked, alerts the user.
        if (userActDsGateway.isLiked(actionerName, targetName)){
            return presenterInterface.prepareFailView("User already liked!");
        }
        // save like information
        userActDsGateway.setLike(actionerName, targetName);
        //present success view.
        return presenterInterface.prepareSuccessView(targetName);
    }



}
