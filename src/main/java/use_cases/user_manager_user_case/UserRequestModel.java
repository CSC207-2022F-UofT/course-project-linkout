package use_cases.user_manager_user_case;

import entities.Profile;

import java.util.Hashtable;
import java.util.List;

public class UserRequestModel {
    private final String accName;
    private float restrictionTime = 0;

    public UserRequestModel(String accName, float time){
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
