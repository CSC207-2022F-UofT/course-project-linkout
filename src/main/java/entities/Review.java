package entities;

public class Review {
    private float rating;
    private String comment;
    private static int count = 0;
    private int id;

    /**
     * Create a new Review with rating, comment, and its unique id (start from 1)
     *
     * @param rating the rating of this single Review.
     * @param comment the comment of this single Review.
     */

    public Review(float rating, String comment) {
        this.rating = rating;
        this.comment = comment;
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                ", id=" + id +
                '}';
    }
}
