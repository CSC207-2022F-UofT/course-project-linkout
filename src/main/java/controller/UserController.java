package controller;

import use_cases.UserInputBoundary;
import use_cases.UserRequestModel;
import use_cases.UserResponseModel;

public class UserController {

    private final UserInputBoundary userInput;

    public UserController(UserInputBoundary interactor){ this.userInput = interactor;}

    public UserResponseModel viewLiked(UserRequestModel userRequestModel){ return userInput.viewLiked(userRequestModel);}

    public UserResponseModel viewAccountStatus(UserRequestModel userRequestModel){return userInput.viewAccountStatus(userRequestModel);}

    public UserResponseModel showVIPStatus(){}

    public UserResponseModel showRestrictedStatus(){}

    public void changeVIPStatus(){}

    public UserResponseModel getRestrictionTime(){}

    public void setRestrictionTime(){}

    public UserResponseModel showProfile(){}

    public UserResponseModel countDownRestriction(){}
}
