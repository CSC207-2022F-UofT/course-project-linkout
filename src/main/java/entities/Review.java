package entities;




import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class Review{
    private int rating;
    private String comment;
    private String writer;
    private String receiver;
    private static int count;
    private int id;


    /**
     * Create a new Review with rating, comment, user and its unique id (start from 1)
     *
     * @param rating the rating of this single Review (1,2,3,4,5).
     * @param comment the comment of this single Review.
     * @param writer the accountname of the user who wrote this single Review.
     * @param receiver the accountname of the user who receives this single Review.
     */

    public Review(int rating, String comment, String writer, String receiver) {
        this.rating = rating;
        this.comment = comment;
        this.writer = writer;
        this.receiver = receiver;
        this.id = count++;
    }

    /**
     * Overload the Review construtor. If the Review is already in the database, create a new Review
     * with rating, comment, user and its existing id .
     *
     * @param rating the rating of this single Review (1,2,3,4,5).
     * @param comment the comment of this single Review.
     * @param writer the accountname of the user who wrote this single Review.
     * @param receiver the accountname of the user who receives this single Review.
     * @param id the id of the eisting Review.
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


    public String getWriter() throws IOException, InvalidAttributeValueException {
        return writer;

    }

    public void setWriter(String writer) throws IOException, InvalidAttributeValueException {
        this.writer = writer;
    }

    public String getReceiver() throws IOException, InvalidAttributeValueException {
        return receiver;
    }
}
