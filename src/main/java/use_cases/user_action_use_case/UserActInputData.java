package use_cases.user_action_use_case;

// use case layer
public class UserActInputData {
    private final String accName;
    private final String targetName;

    //private boolean isLiking = true;

    public UserActInputData(String accName, String targetName) {
        this.accName = accName;
        this.targetName = targetName;
    }

    public String getAccName() {
        return accName;
    }

    public String getTargetName() {
        return targetName;
    }


}
