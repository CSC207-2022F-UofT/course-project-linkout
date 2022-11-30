package entities;

public class Review{
    private int rating;
    private String comment;
    private String writer;
    private String receiver;
    private int id;


    /**
     * Review construtor. If the Review is not already in the database, create a new Review
     * with rating, comment, user and its unique id .
     *
     * @param rating the rating of this single Review (1,2,3,4,5).
     * @param comment the comment of this single Review.
     * @param writer the accountname of the user who wrote this single Review.
     * @param receiver the accountname of the user who receives this single Review.
     * @param id the id of the Review.
     */
    public Review(int rating, String comment, String writer, String receiver, int id) {
        this.rating = rating;
        this.comment = comment;
        this.writer = writer;
        this.receiver = receiver;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getReceiver() {
        return receiver;
    }
}
