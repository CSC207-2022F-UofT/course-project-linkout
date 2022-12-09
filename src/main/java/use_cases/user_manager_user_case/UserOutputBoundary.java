package use_cases.user_manager_user_case;

import entities.Profile;

import java.util.Hashtable;
import java.util.List;

public interface UserOutputBoundary {
    void prepareLikedView(List<String> liked);

    void prepareFailedView(String e);

    void prepareAccStatusView(boolean status, float time);


    void prepareSuccessView(boolean status);

    void prepareProfileView(Profile profile);

    void prepareLikedMeView(List<String> likedMe);

    void prepareReviewView(Hashtable<Integer, List<Object>> reviews);
}
