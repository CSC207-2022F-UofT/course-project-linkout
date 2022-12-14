package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;


public class VipUser extends User{

    private boolean isInvisible;
    private List<String> visitors;
    private Hashtable<Integer, List<Object>> hiddenReviews = new Hashtable<>();

    /**
     * Create a VIP user with its profile when isVIP status is true.
     *
     * @param profile the profile for this VIP user.
     * @param isVIP the VIP status for this VIP user.
     */


    public VipUser(String password, String accountName, Profile profile, boolean isVIP,
                   List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        super(password, accountName, profile, isVIP, liked, likedme, reviews);
    }

    public boolean setInvisible(boolean arg){
        this.isInvisible = arg;
        return true;
    }


    public boolean getInvisibleStatus(){
        return this.isInvisible;
    }

    public List<String> showLikedMe(){
        return super.showLikedMe();
    }

    public List<String> showVisitor(){
        return this.visitors;
    }

    public void addVisitor(String visitor){
        this.visitors.add(visitor);
    }

    public boolean hideReview(Integer review_ids){
       if (this.getReviews().containsKey(review_ids)){
           hiddenReviews.put(review_ids, this.getReviews().get(review_ids));
           this.deleteReview(review_ids);
            }
            else {
                return false ;
            }
        return true;
    }

}
