package use_cases.user_manager_user_case;

import entities.Profile;

import java.util.Hashtable;
import java.util.List;

public interface UserOutputBoundary {
    public void prepareLikedView(List<String> liked);

    public void prepareFailedView(String e);

    public void prepareAccStatusView(boolean status, float time);


    public void prepareSuccessView(boolean status);

    public void prepareProfileView(Profile profile);

    public void prepareLikedMeView(List<String> likedMe);

    public void prepareReviewView(Hashtable<Integer, List<Object>> reviews);
}
