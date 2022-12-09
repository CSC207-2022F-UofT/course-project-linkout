package use_cases.user_manager_user_case;


import use_cases.regular_user_register_use_case.UserGateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class UserController {

    private final UserInputBoundary userInput;

    public UserController(UserInputBoundary interactor){
        this.userInput = interactor;
    }

    /**
     * @param userRequestModel the userRequestModel that contains user information
     */
    public void viewLiked(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException { userInput.viewLiked(userRequestModel);}

    /**
     * @param userRequestModel the userRequestModel that contains user information
     */
    public void viewAccountStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException { userInput.viewAccountStatus(userRequestModel);}

    /**
     * @param userRequestModel the userRequestModel that contains user information
     */
    public void changeVIPStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        RegularUserManager regularUserManager = (RegularUserManager) userInput;
        regularUserManager.upgrade(userRequestModel);}



    /**
     * @param userRequestModel the userRequestModel that contains user information
     */
    public void showProfile(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {userInput.showProfile(userRequestModel);}

    /**
     * @param userRequestModel the userRequestModel that contains user information
     */
    public void viewLikedMeVIP(UserRequestModel userRequestModel)throws IOException, InvalidAttributeValueException{
        ((VIPUserManager) userInput).showLikeMe(userRequestModel);
    }

    /**
     * @param userRequestModel the userRequestModel that contains user information
     */
    public void displayReview(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        userInput.showReview(userRequestModel);
    }


    /**
     * @param userRequestModel the userRequestModel that contains user information
     * @param yesNo boolean, current user invisible status
     */
    public void setInvisibleVisit(UserRequestModel userRequestModel, boolean yesNo) throws IOException, InvalidAttributeValueException {
        ((VIPUserManager) userInput).invisibleVisit(userRequestModel, yesNo);
    }
}