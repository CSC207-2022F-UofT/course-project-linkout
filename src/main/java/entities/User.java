package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class User extends Account {

    private Profile profile;
    private List<User> blocked = new ArrayList<>();
    private boolean isVIP = false;
    private List<User> liked = new ArrayList<>();
    private List<User> likedme = new ArrayList<>();
    private Hashtable<Integer, List<Object>> reviews = new Hashtable<>();
    private int restrictionDuration;
    private long restrictionInitialTime;
    private List<Report> reports = new ArrayList<>();

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

    /**
     * Adds a report to the user's list of reports.
     * @param report    The report filed against this user.
     */
    public void addReport(Report report) {
        reports.add(report);
    }

    /**
     * Gets the newest report filed against this user.
     * @return      The report
     */
    public Report getNewestReport() { return reports.get(reports.size() - 1); }

    /**
     * Adds a review to the user's list of reports.
     * @param review    The review.
     */
    public void addReviews(Review review){
        Integer revId = review.getId();
        List<Object> revBody = extractReviewBody(review);
        this.reviews.put(revId, revBody);
    }

    /**
     * Will return true if restriction is cleared. This is determined by seeing
     * whether the restriction duration, in seconds, have fully elapsed since the user
     * was restricted.
     *
     * @return  True or false.
     */
    public boolean isRestricted(){
        return System.currentTimeMillis() >= restrictionInitialTime + restrictionDuration;
    }

    /**
     * Gets the text from a review.
     * @param review    The review object that we want to get the text from.
     * @return          The text of the review.
     */
    private List<Object> extractReviewBody(Review review){
        List<Object> revBody = new ArrayList<>();
        revBody.add(review.getRating());
        revBody.add(review.getComment());
        return revBody;
    }

    /**
     * Sees if the password of this user is valid.
     * @return  True or false
     */
    public boolean passwordIsValid() {
        return this.getPassword().length() >= 3;
    }

    /**
     * Remove the review with the given id.
     * @param id    The id of the review.
     */
    public void deleteReview(int id){
        this.reviews.remove(id);
    }

    // Getters and setters
    public float getRestrictionDuration() { return restrictionDuration; }
    public void setRestrictionDuration(int restrictionDuration) {
        this.restrictionDuration = restrictionDuration;
        this.restrictionInitialTime = System.currentTimeMillis();
    }
    public Profile displayProfile(){ return this.profile;}
    public List<User> showBlocked(){ return this.blocked;}
    public List<User> showLiked(){ return this.liked;}
    public boolean showVip(){ return this.isVIP;}
    public void setVipStatus(boolean isvip){ this.isVIP = isvip;}
    public List<User> showLikedMe(){ return this.likedme;}
    public Hashtable<Integer, List<Object>> getReviews(){ return this.reviews;}
}