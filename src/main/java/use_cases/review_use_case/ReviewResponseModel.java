package use_cases.review_use_case;

public class ReviewResponseModel {
    private String review;
    private String status;
    private String creationTime;


    /**
     * Create a new ReviewResponseModel with a String representing the Review and a String
     * representing the status of the Review
     *
     * @param review the String representation of the review
     * @param status the status of the review (added, deleted, hided)
     * @param creationTime the time of the action (add, delete, hide) on the review
     */

    public ReviewResponseModel(String review, String status, String creationTime) {
        this.review = review;
        this.status = status;
        this.creationTime = creationTime;
    }

    /**
     * Overload the constructor, used when deleting and hiding a review
     *
     * @param status the status of the review (added, deleted, hided)
     * @param creationTime the time of the action (add, delete, hide) on the review
     */
    public ReviewResponseModel(String status, String creationTime){
        this.status = status;
        this.creationTime = creationTime;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
