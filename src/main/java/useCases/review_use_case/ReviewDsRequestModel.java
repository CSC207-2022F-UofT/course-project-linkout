package useCases.review_use_case;

import java.time.LocalDateTime;

public class ReviewDsRequestModel {
    private final String writername;
    private final String receivername;
    private final int rating;
    private final String comment;
    private final LocalDateTime creationTime;


    public ReviewDsRequestModel(String writername, String receivername, int rating, String comment, LocalDateTime creationTime) {
        this.writername = writername;
        this.receivername = receivername;
        this.rating = rating;
        this.comment = comment;
        this.creationTime = creationTime;
    }

    public String getWritername() {
        return writername;
    }

    public String getReceivername() {
        return receivername;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
