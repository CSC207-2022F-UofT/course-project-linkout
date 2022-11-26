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
    public String action(UserActInputData inputData) {
        String accName = inputData.getAccName();
        String targetName = inputData.getTargetName();
        boolean isLiking = inputData.isLiking();
        //if action already made
        if (userActDsGateway.existByName(accName, targetName)){
            if (isLiking){
                return presenterInterface.prepareFailView("User already liked!");
            }
            return presenterInterface.prepareFailView("Action already Made!");
        }
        // save like information
        UserActDsRequestModel dsRequestDSModel = new UserActDsRequestModel(accName, targetName);
        userActDsGateway.save(dsRequestDSModel);

        // check if matched
        if (isLiking && userActDsGateway.existByName(targetName, accName) ){
            return presenterInterface.prepareMatchingView(targetName);
        }
        //present liked account name
        return presenterInterface.prepareSuccessView(targetName);
    }



}
