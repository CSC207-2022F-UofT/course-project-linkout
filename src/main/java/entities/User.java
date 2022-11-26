package entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class User extends Account{

    private Profile profile;
    private List<User> blocked = new ArrayList<>();
    private boolean isVIP = false;
    private List<String> liked = new ArrayList<>();
    private List<String> likedme = new ArrayList<>();
    private Hashtable<Integer, List<Object>> reviews = new Hashtable<>();
    private float restrictedTime = 0;


    /**
     * Create a user with its profile and can be a vip user if isVIP param is true.
     *
     * @param profile the profile for this user.
     * @param isVIP the VIP status of this user.
     */

    // For VIPUser constructor
    public User(String password, String accountName, Profile profile, boolean isVIP,
                List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        super(password, accountName);
        this.profile = profile;
        this.isVIP = isVIP;
        this.liked = liked;
        this.likedme = likedme;
        this.reviews = reviews;
    }

    // For RegularUser constructor
    public User(String password, String accountName, Profile profile,
                List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        super(password, accountName);
        this.profile = profile;
        this.liked = liked;
        this.likedme = likedme;
        this.reviews = reviews;
    }

    public float getRestrictedTime(){ return this.restrictedTime;}

    public void setRestrictedTime(float setTime){ this.restrictedTime = setTime;}

    public Profile displayProfile(){ return this.profile;}

    public List<User> showBlocked(){ return this.blocked;}

    public List<String> showLiked(){ return this.liked;}

    public boolean showVip(){ return this.isVIP;}

    public void setVipStatus(boolean isvip){ this.isVIP = isvip;}

    public List<String> showLikedMe(){ return this.likedme;}

    public Hashtable<Integer, List<Object>> getReviews(){ return this.reviews;}

    public void addReviews(Review review){
        Integer revId = review.getId();
        List<Object> revBody = extractReviewBody(review);
        this.reviews.put(revId, revBody);
    }

    public boolean countDownRestrictionTime(){
        LocalDateTime expire = LocalDateTime.now().plusSeconds((long)this.restrictedTime);
        while (LocalDateTime.now().compareTo(expire) < 0){
            this.restrictedTime = Duration.between(expire, LocalDateTime.now()).getSeconds();
            if (this.restrictedTime > 60) {
                System.out.println("More than 1 minutes");
            }else{
                System.out.println(this.restrictedTime);
            }
        }
        return true; // will return true if restriction is cleared.
    }

    private List<Object> extractReviewBody(Review review){
        List<Object> revBody = new ArrayList<>();
        revBody.add(review.getRating());
        revBody.add(review.getComment());
        return revBody;
    }


    public boolean passwordIsValid() {
        if (this.getPassword().length() >= 3){
            return true;
        }
        return false;
    }
}
