package entities;

import java.util.ArrayList;

abstract class User {

    private Profile profile;
    private ArrayList<User> blocked = new ArrayList<>();
    private boolean isVIP = false;
    private ArrayList<User> liked = new ArrayList<>();
    private ArrayList<User> likedme = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<Review>();
    private float restrictedTime = 0;


    // For VIPUser constructor
    public User(Profile profile, boolean isVIP){
        this.profile = profile;
        this.isVIP = isVIP;
    }


    // For RegularUser constructor
    public User(Profile profile){
        this.profile = profile;
    }

    public float getRestrictedTime(){
        return this.restrictedTime;
    }

    public void setRestrictedTime(float setTime){
        this.restrictedTime = setTime;
    }

    public Profile displayProfile(){
        return this.profile;
    }

    public ArrayList<User> showBlocked(){
        return this.blocked;
    }

    public ArrayList<User> showLiked(){
        return this.liked;
    }

    public ArrayList<User> showLikedMe(){
        return this.likedme;
    }

    public ArrayList<Review> getReviews(){
        return this.reviews;
    }

    public void addReviews(Review review){
        this.reviews.add(review);
    }

}
