package screens;

import user_action_use_case.UserActInputBoundary;
import user_action_use_case.UserActInputData;

public class UserActController {

    private final UserActInputBoundary userInput;


    public UserActController(UserActInputBoundary userInput) {
        this.userInput = userInput;
    }

    String action(String accName, String targetName, boolean isLiking){
        //RequestModel
        UserActInputData inputData = new UserActInputData(accName, targetName, isLiking);
        return userInput.action(inputData);
    }

}
