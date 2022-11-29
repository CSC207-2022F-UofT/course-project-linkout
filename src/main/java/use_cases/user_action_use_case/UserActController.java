package use_cases.user_action_use_case;

// Interface Adapter Layer
public class UserActController {

    private final UserActInputBoundary userInput;


    public UserActController(UserActInputBoundary userInput) {
        this.userInput = userInput;
    }


    /** A user wants to like another user.
     * @param accName like performer's account name
     * @param targetName target user's account name
     * @return a string which is a message created by presenter.
     */
    public String like(String accName, String targetName){
        //RequestModel
        UserActInputData inputData = new UserActInputData(accName, targetName);
        return userInput.like(inputData);
    }

}
