package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class User extends Account{

    private Profile profile;
    private ArrayList<User> blocked = new ArrayList<>();
    private boolean isVIP = false;
    private List<User> liked = new ArrayList<>();
    private List<User> likedme = new ArrayList<>();
    private Hashtable<Integer, List<Object>> reviews = new Hashtable<>();
    private float restrictedTime = 0;


    /**
     * Create a user with its profile and can be a vip user if isVIP param is true.
     *
     * @param profile the profile for this user.
     * @param isVIP the VIP status of this user.
     */

    // For VIPUser constructor
    public User(String password, String accountName, Profile profile, boolean isVIP){
        super(password, accountName);
        this.profile = profile;
        this.isVIP = isVIP;
    }

    // For RegularUser constructor
    public User(String password, String accountName, Profile profile){
        super(password, accountName);
        this.profile = profile;
    }

    public float getRestrictedTime(){ return this.restrictedTime;}

    public void setRestrictedTime(float setTime){ this.restrictedTime = setTime;}

    public Profile displayProfile(){ return this.profile;}

    public List<User> showBlocked(){ return this.blocked;}

    public List<User> showLiked(){ return this.liked;}

    public List<User> showLikedMe(){ return this.likedme;}

    public Hashtable<Integer, List<Object>> getReviews(){ return this.reviews;}

    public void addReviews(Review review){
        Integer revId = review.getId();
        List<Object> revBody = extractReviewBody(review);
        this.reviews.put(revId, revBody);
    }

    private List<Object> extractReviewBody(Review review){
        List<Object> revBody = new ArrayList<>();
        revBody.add(review.getRating());
        revBody.add(review.getComment());
        return revBody;
    }


}
