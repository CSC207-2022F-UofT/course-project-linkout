package use_cases.review_use_case;

public class ReviewRequestModel {
    private int rating;
    private String comment;
    private String writer;
    private String receiver;
    private boolean isComplete;


    /**
     * Create a new ReviewRequestModel with rating,comment,writer and receiver according to the input data. If the
     * ReviewRequestModel has positive rating and inputs the comment, writer and receiver's name, then it is considered
     * to be complete.
     *
     * @param rating the rating of the input review
     * @param comment the comment of the input review
     * @param writer the accountname of the user who receives the review
     * @param receiver the accountname of the user who receives the review
     */


    public ReviewRequestModel(int rating, String comment, String writer, String receiver) {
        this.rating = rating;
        this.comment = comment;
        this.writer = writer;
        this.receiver = receiver;
        this.isComplete = rating > 0 && comment != null && writer != null && receiver != null;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getWriter() {
        return writer;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean isComplete() {
        return isComplete;
    }

}
