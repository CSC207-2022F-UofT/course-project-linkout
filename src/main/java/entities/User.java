package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class User extends Account {

    private Profile profile;
    private boolean isVIP = false;


    private List<String> liked = new ArrayList<>();
    private List<String> likedme = new ArrayList<>();
    protected Hashtable<Integer, List<Object>> reviews = new Hashtable<>();
    private int restrictionDuration;
    private float restrictionInitialTime;
    private List<Report> reports = new ArrayList<>();


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

    // For VIPUser constructor.
    public User(String password, String accountName, Profile profile, boolean isVIP){
        super(password, accountName);
        this.profile = profile;
        this.isVIP = isVIP;
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


    // For RegularUser constructor.
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


    public boolean setRestrictedTime(float setTime){
        this.restrictionInitialTime = setTime;
        return true;
    }

    public Profile displayProfile(){ return this.profile;}

    public List<String> showLiked(){ return this.liked;}

    public boolean showVip(){ return this.isVIP;}

    public boolean setVipStatus(boolean isvip){
        this.isVIP = isvip;
        return true;
    }

    public List<String> showLikedMe(){ return this.likedme;}

    public Hashtable<Integer, List<Object>> getReviews(){ return this.reviews;}

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

    public void like(String targetName) {
        this.liked.add(targetName);
    }
    public String findContactInfo(){
        return this.profile.getContactInformation();
    }

    // Getters and setters
    public float getRestrictionDuration() { return restrictionDuration; }
    public void setRestrictionDuration(int restrictionDuration) {
        this.restrictionDuration = restrictionDuration;
        this.restrictionInitialTime = System.currentTimeMillis();

    }

    public float calculateRemainingTime(){
        float currentTime = System.currentTimeMillis();
        if (currentTime >= restrictionDuration + restrictionInitialTime){
            return 0;
        }else{
            return restrictionInitialTime + restrictionDuration - currentTime;
        }
    }


}
