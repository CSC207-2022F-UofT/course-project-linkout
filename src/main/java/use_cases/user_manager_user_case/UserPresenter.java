package use_cases.user_manager_user_case;

import entities.Profile;
import screens.user_info_screen.UserInformation;

import java.util.Hashtable;
import java.util.List;


public class UserPresenter implements UserOutputBoundary {

    private final UserInformation userInfoScreen;

    public UserPresenter(UserInformation userInfoScreen) {
        this.userInfoScreen = userInfoScreen;
    }

    /**
     * @param liked a list of user account names of the current user likes
     */
    @Override
    public void prepareLikedView(List<String> liked) {
        userInfoScreen.showLiked(liked);
    }

    /**
     * @param e fail message
     */
    @Override
    public void prepareFailedView(String e) {
        userInfoScreen.showFailedMessage(e);
    }

    /**
     * @param status user VIP status
     * @param time user Restricted Duration
     */
    @Override
    public void prepareAccStatusView(boolean status, float time) {
        userInfoScreen.showAccStatus(status, time);
    }


    /**
     * @param status true if operation is successful
     */
    @Override
    public void prepareSuccessView(boolean status) {
        userInfoScreen.showSuccessMessage("Success!");
    }

    /**
     * @param profile user's profile that need to show
     */
    @Override
    public void prepareProfileView(Profile profile) {
        userInfoScreen.showProfile(profile);
    }

    /**
     * @param likedMe List of account names of the users that likes the current user
     */
    @Override
    public void prepareLikedMeView(List<String> likedMe) {
        userInfoScreen.showLikedMe(likedMe);
    }


    /**
     * @param reviews a hashtable of review id as key, a list of rating, comment as the value of hashtable
     */
    public void prepareReviewView(Hashtable<Integer, List<Object>> reviews){
        userInfoScreen.showReview(reviews);
    }
}