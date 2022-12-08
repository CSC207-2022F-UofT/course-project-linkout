package use_cases.user_action_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

// Interface Adapter Layer
public class UserActController {

    private final UserActInputBoundary userInput;


    public UserActController(UserActInputBoundary userInput) {
        this.userInput = userInput;
    }


    /** A user wants to like another user.
     * Precondition: Two users must be registered.
     * @param accName like performer's account name
     * @param targetName target user's account name
     * @return a string which is a message created by presenter.
     */
    public String like(String accName, String targetName) throws InvalidAttributeValueException {
        //RequestModel
        UserActInputData inputData = new UserActInputData(accName, targetName);
        try {
            return userInput.like(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

}