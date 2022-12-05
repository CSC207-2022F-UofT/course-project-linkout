package use_cases.user_manager_user_case;


import use_cases.*;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private final UserInputBoundary userInput;

    public UserController(UserInputBoundary interactor){ this.userInput = interactor;}

    public void viewLiked(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException { userInput.viewLiked(userRequestModel);}

    public void viewAccountStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException { userInput.viewAccountStatus(userRequestModel);}


    public void changeVIPStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        ((RegularUserManager) userInput).upgrade(userRequestModel);}


    public void setRestrictionTime(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        userInput.setRestrictionTime(userRequestModel);
    }

    public void showProfile(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {userInput.showProfile(userRequestModel);}

    public void viewLikedMeVIP(UserRequestModel userRequestModel)throws IOException, InvalidAttributeValueException{
        ((VIPUserManager) userInput).showLikeMe(userRequestModel);
    }

    public void displayReview(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        userInput.showReview(userRequestModel);
    }
    public void hideReviewVIP(UserRequestModel userRequestModel, Integer revIds) throws IOException, InvalidAttributeValueException {
        ((VIPUserManager) userInput).hideReview(userRequestModel, revIds);
    }

    public void setInvisibleVisit(UserRequestModel userRequestModel, boolean yesNo) throws IOException, InvalidAttributeValueException {
        ((VIPUserManager) userInput).invisibleVisit(userRequestModel, yesNo);
    }
}
