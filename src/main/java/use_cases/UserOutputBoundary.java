package use_cases;

import entities.Profile;

import java.util.List;

public interface UserOutputBoundary {
    public void prepareLikedView(List<String> liked);

    public void prepareFailedView(String e);

    public void prepareAccStatusView(boolean status, float time);

    public void prepareVipStatusView(boolean status);

    public void prepareRestrictionStatusView(float time);

    public void prepareSuccessView(boolean status);

    public void prepareProfileView(Profile profile);

    public void prepareLikedMeView(List<String> likedMe);

    public void prepareVisitorView(List<String> visitor);
}
