package screens.review_screen;

import entities.Profile;

import javax.swing.*;
import java.util.Hashtable;
import java.util.List;

public interface UserInformation {
    public void showLiked(List<String> accName);

    public void showFailedMessage(String message);

    public void showAccStatus(boolean status, float time);

    public void showSuccessMessage(String message);

    public void showProfile(Profile profile);

    public void showReview(Hashtable<Integer, List<Object>> reviews);

    public void showLikedMe(List<String> likedme);

}
