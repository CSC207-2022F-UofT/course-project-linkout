package useCases.review_use_case;

public class ReviewResponseModel {
    private String review;
    private String creationTime;
    private String status;

    /**
     * Create a new ReviewResponseModel with a String representing the Review, a String
     * representing the creation time and s String representing the status of the Review
     *
     * @param review the String representation of the review
     * @param creationTime the creation time of the review
     * @param status the status of the review (added, deleted, hided)
     */

    public ReviewResponseModel(String review, String creationTime, String status) {
        this.review = review;
        this.creationTime = creationTime;
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReviewResponseModel{" +
                "review='" + review + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
