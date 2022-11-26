package presenter;

import entities.Profile;
import entities.User;
import screen.UserInformation;
import use_cases.UserOutputBoundary;
import use_cases.UserResponseModel;

import java.util.List;

public class UserPresenter implements UserOutputBoundary {

    private final UserResponseModel userResponseModel;
    private final UserInformation userInfoScreen;

    public UserPresenter(UserResponseModel userResponseModel, UserInformation userInfoScreen){
        this.userResponseModel = userResponseModel;
        this.userInfoScreen = userInfoScreen;
    }
    @Override
    public void prepareLikedView(List<String> liked) {
        userInfoScreen.showLiked(liked);
    }

    @Override
    public void prepareFailedView(String e) {
        userInfoScreen.showFailedMessage(e);
    }

    @Override
    public void prepareAccStatusView(boolean status, float time) {
        userInfoScreen.showAccStatus(status, time);
    }

    @Override
    public void prepareVipStatusView(boolean status) {
        userInfoScreen.showVipStatus(status);
    }

    @Override
    public void prepareRestrictionStatusView(float time) {
        userInfoScreen.showRestrictionStatus(time);
    }

    @Override
    public void prepareSuccessView(boolean status) {
        userInfoScreen.showSuccessMessage("Success!");
    }

    @Override
    public void prepareProfileView(Profile profile) {
        userInfoScreen.showProfile(profile);
    }

    @Override
    public void prepareLikedMeView(List<String> likedMe){
        userInfoScreen.showLikedMe(likedMe);
    }

    public void prepareVisitorView(List<String> visitor){
        userInfoScreen.showVisitor(visitor);
    }
}
