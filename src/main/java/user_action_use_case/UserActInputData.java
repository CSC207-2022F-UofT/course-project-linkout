package user_action_use_case;

public class UserActInputData {
    private final String accName;
    private final String targetName;

    private final boolean isLiking;

    public UserActInputData(String accName, String targetName, boolean isLiking) {
        this.accName = accName;
        this.targetName = targetName;
        this.isLiking = isLiking;
    }

    public String getAccName() {
        return accName;
    }

    public String getTargetName() {
        return targetName;
    }


    public boolean isLiking() {
        return isLiking;
    }
}
