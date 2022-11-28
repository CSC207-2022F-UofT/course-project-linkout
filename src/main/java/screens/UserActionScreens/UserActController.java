package screens.UserActionScreens;

import user_action_use_case.UserActInputBoundary;
import user_action_use_case.UserActInputData;

// Interface Adapter Layer
public class UserActController {

    private final UserActInputBoundary userInput;


    public UserActController(UserActInputBoundary userInput) {
        this.userInput = userInput;
    }

    String like(String accName, String targetName){
        //RequestModel
        UserActInputData inputData = new UserActInputData(accName, targetName);
        return userInput.like(inputData);
    }

}
