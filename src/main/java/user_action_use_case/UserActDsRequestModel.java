package user_action_use_case;


// What to save in database
public class UserActDsRequestModel {
    private final String accName;
    private String targetName;
    private boolean like;

    public UserActDsRequestModel(String accName, String targetName, boolean like) {
        this.accName = accName;
        this.targetName = targetName;
        this.like = like;
    }

    public String getAccName(){return accName;}
    public String getTargetName(){return targetName;}
    public boolean isLiking(){return like;}
}
