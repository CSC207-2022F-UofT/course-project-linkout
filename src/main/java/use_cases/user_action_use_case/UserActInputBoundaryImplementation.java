package use_cases.user_action_use_case;

public class UserActInputBoundaryImplementation implements UserActInputBoundary {
    private UserActInputData inputData;

    public String like(UserActInputData inputData){
        String accName = inputData.getAccName();
        String targetName = inputData.getTargetName();
        boolean isLiked = inputData.isLiking();

        //not sure about the return
        return "Successfully Liked " + targetName + " !";
        }
}
