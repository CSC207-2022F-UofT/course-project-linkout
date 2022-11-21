package use_cases;

import entities.*;
import java.util.Hashtable;
import java.util.List;

public class UserManagerInteractor {

    protected User user;

    public UserManagerInteractor(User user){
        this.user = user;
    }

    public List<String> viewLiked(){
        return user.showLiked();
    }

    public String viewAccountStatus(){
        return "User is VIP: " + showVIPStatus() + "; User is restricted: " + showRestrictionStatus();
    }

    public boolean showVIPStatus(){
        return user.showVip();
    }

    public boolean showRestrictionStatus(){
        return user.getRestrictedTime() != 0;
    }

    public void changeVIPStatus(boolean isvip){
        user.setVipStatus(isvip);
    }

    public float getRestrictionTime(){
        return user.getRestrictedTime();
    }

    public void setRestrictionTime(float time){
        user.setRestrictedTime(time);
    }

    public Profile showProfile(){
        return user.displayProfile();
    }

    public boolean countDownRestriction(){
        return user.countDownRestrictionTime();
    }



}
