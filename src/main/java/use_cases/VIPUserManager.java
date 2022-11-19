package use_cases;

import entities.User;
import entities.VipUser;

import java.util.ArrayList;
import java.util.List;

public class VIPUserManager extends UserManagerInteractor{


    private VipUser vipUser;

    public VIPUserManager(VipUser vipUser){
        super(vipUser);
        this.vipUser = vipUser;
    }

    public void hideReview(ArrayList<Integer> review_ids){
        vipUser.hideReview(review_ids);
    }

    public void invisibleVisit(boolean invisible){
        vipUser.setInvisible(invisible);
    }

    public List<User> showLikeMe(){
        return vipUser.showLikedMe();
    }

    public List<User> showVisitor(){
        return vipUser.showVisitor();
    }
}
