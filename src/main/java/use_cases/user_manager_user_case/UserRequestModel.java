package use_cases.user_manager_user_case;



public class UserRequestModel {
    private final String accName;
    private float restrictionTime;

    public UserRequestModel(String accName, float time ){
        this.accName = accName;
        this.restrictionTime = time;
    }

    public String getAccName(){
        return accName;
    }

    public float getRestrictionTime(){
        return restrictionTime;
    }
}
