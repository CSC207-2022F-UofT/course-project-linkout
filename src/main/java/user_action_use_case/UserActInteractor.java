package user_action_use_case;
import entities.User;
//import entities.DatabaseConnect;

public class UserActInteractor implements UserActInputBoundary{

    private final UserActDsGateway userActDsGateway;
    private final UserActOutputBoundary outputBoundary;

    public UserActInteractor(UserActDsGateway userActDsGateway, UserActOutputBoundary outputBoundary) {
        this.userActDsGateway = userActDsGateway;
        this.outputBoundary = outputBoundary;
    }


    @Override
    public String action(UserActInputData inputData) {
        String accName = inputData.getAccName();
        String targetName = inputData.getTargetName();
        boolean isLiking = inputData.isLiking();
        //if action already made
        if (userActDsGateway.existByName(accName, targetName, isLiking)){
            if (isLiking){
                return outputBoundary.prepareFailView("User already liked.");
            }
            return outputBoundary.prepareFailView("error!");
        }
        // save like information
        UserActDsRequestModel dsRequestDSModel = new UserActDsRequestModel(accName, targetName, isLiking);
        userActDsGateway.save(dsRequestDSModel);

        // check if matched
        if (isLiking && userActDsGateway.existByName(targetName, accName, true) ){
            return outputBoundary.prepareMatchingView(targetName);
        }
        //present liked account name
        return outputBoundary.prepareSuccessView(targetName);
    }



}
