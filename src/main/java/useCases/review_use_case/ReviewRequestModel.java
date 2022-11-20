package useCases.review_use_case;

public class ReviewRequestModel {
    private int rating;
    private String comment;
    private String user;
    private boolean isComplete;


    /**
     * Create a new ReviewRequestModel with rating and comment according to the input data
     *
     * @param rating the rating of the input review
     * @param comment the comment of the input review
     * @param user the user who receives the review
     */


    public ReviewRequestModel(int rating, String comment, String user) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.isComplete = rating > 0 && comment != null && user != null;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}