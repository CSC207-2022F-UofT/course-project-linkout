package screen;

import entities.Profile;

import java.util.List;

public interface UserInformation {
    public void showLiked(List<String> accName);

    public void showFailedMessage(String message);

    public void showAccStatus(boolean status, float time);

    public void showVipStatus(boolean status);

    public void showRestrictionStatus(float time);

    public void showSuccessMessage(String message);

    public void showProfile(Profile profile);

    public void showLikedMe(List<String> likedme);

    public void showVisitor(List<String> visitor);
}
