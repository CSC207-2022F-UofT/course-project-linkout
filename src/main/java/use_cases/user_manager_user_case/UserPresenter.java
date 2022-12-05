package use_cases.user_manager_user_case;

import entities.Profile;
import entities.User;
import screens.user_info_screen.UserInformation;
import use_cases.user_manager_user_case.UserOutputBoundary;
import use_cases.user_manager_user_case.UserResponseModel;

import java.util.Hashtable;
import java.util.List;

public class UserPresenter implements UserOutputBoundary {

    private final UserInformation userInfoScreen;

    public UserPresenter(UserInformation userInfoScreen) {
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
    public void prepareSuccessView(boolean status) {
        userInfoScreen.showSuccessMessage("Success!");
    }

    @Override
    public void prepareProfileView(Profile profile) {
        userInfoScreen.showProfile(profile);
    }

    @Override
    public void prepareLikedMeView(List<String> likedMe) {
        userInfoScreen.showLikedMe(likedMe);
    }


    public void prepareReviewView(Hashtable<Integer, List<Object>> reviews){
        userInfoScreen.showReview(reviews);
    }
}
