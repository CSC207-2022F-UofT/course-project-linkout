package use_cases;

public interface UserInputBoundary {

    public void viewLiked(UserRequestModel userRequestModel);

    public void viewAccountStatus(UserRequestModel userRequestModel);

    public void showVIPStatus(UserRequestModel userRequestModel);

    public void showRestrictedStatus(UserRequestModel userRequestModel);

    public void changeVIPStatus(UserRequestModel userRequestModel);

    public void getRestrictionTime(UserRequestModel userRequestModel);

    public void setRestrictionTime(UserRequestModel userRequestModel);

    public void showProfile(UserRequestModel userRequestModel);

}
