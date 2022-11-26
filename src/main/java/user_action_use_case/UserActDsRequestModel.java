package user_action_use_case;


// What to save in database
public class UserActDsRequestModel {
    private final String accName;
    private final String targetName;
    private boolean isLiking = true;

    public UserActDsRequestModel(String accName, String targetName) {
        this.accName = accName;
        this.targetName = targetName;
    }
    public UserActDsRequestModel(String accName, String targetName, boolean isLiking) {
        this.accName = accName;
        this.targetName = targetName;
        this.isLiking = isLiking;
    }

    public String getAccName(){return accName;}
    public String getTargetName(){return targetName;}
    public boolean isLiking(){return isLiking;}
}
