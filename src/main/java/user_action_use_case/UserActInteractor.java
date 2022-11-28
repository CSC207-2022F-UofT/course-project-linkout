package user_action_use_case;
//import entities.DatabaseConnect;

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
        //if action already made
        if (userActDsGateway.isLiked(accName, targetName)){
            return presenterInterface.prepareFailView("User already liked!");
        }
        // save like information
        UserActDsRequestModel dsRequestDSModel = new UserActDsRequestModel(accName, targetName);
        userActDsGateway.save(dsRequestDSModel);

        // check if matched
        if (userActDsGateway.isLiked(targetName, accName) ){
            return presenterInterface.prepareMatchingView(targetName);
        }
        //present liked account name
        return presenterInterface.prepareSuccessView(targetName);
    }



}
