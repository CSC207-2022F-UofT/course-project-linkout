package restrict_user_use_case;

public class RestrictUserInputData {
    private String userID;
    private int restrictionDuration;

    public RestrictUserInputData(String userID, int restrictionDuration) {
        this.userID = userID;
        this.restrictionDuration = restrictionDuration;
    }

    public RestrictUserInputData(String userID) {
        this.userID = userID;
        this.restrictionDuration = 0;
    }

    /*
    Public getters.
     */
    public String getUserID() { return userID; }
    public int getRestrictionDuration() { return restrictionDuration; }
}
