package use_cases;

import entities.Profile;
import entities.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserManagerInteractor {


    protected User user;

    public UserManagerInteractor(User user){
        this.user = user;
    }

    public List<User> viewLiked(){
        return user.showLiked();
    }

    public boolean viewAccountStatus(){
        return user.showVip();
    }

    public void changeAccountStatus(boolean isvip){
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
