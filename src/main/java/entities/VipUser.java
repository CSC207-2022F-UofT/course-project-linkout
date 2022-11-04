package entities;

import java.util.ArrayList;

public class VipUser extends User{

    private boolean isInvisible = false;
    private ArrayList<User> visitors = new ArrayList<>();
    private ArrayList<Review> hiddenRe;


    public VipUser(Profile profile, boolean isVIP){
        super(profile, isVIP);
    }

    public void setInvisible(){
        this.isInvisible = true;
    }

    public ArrayList<User> showLikedMe(){
        return super.showLikedMe();
    }

    public void hideReview(ArrayList<Review> reviews){
//        for (Review rev : reviews){
//            if ()
//        }
    }

}
