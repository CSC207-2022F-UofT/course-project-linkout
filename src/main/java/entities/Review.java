package entities;

public class Review implements Savable{
    private float rating;
    private String comment;
    private User user;
    private static int count;
    private int id;

    /**
     * Create a new Review with rating, comment, user and its unique id (start from 1)
     *
     * @param rating the rating of this single Review.
     * @param comment the comment of this single Review.
     * @param user the user who receives this single Review.
     */

    public Review(float rating, String comment, User user) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", id=" + id +
                '}';
    }


    @Override
    public String toSavableFormat() {
        return rating + "," + comment + user.toString() + "," + "," + id;
    }
}
