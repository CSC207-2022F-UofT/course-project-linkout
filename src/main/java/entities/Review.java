package entities;

public class Review{
    private int rating;
    private String comment;
    private String username;

    private String userviewed;
    private static int count;
    private int id;

    private DatabaseConnect db = new DatabaseConnect(System.getProperty("user.dir"));

    /**
     * Create a new Review with rating, comment, user and its unique id (start from 1)
     *
     * @param rating the rating of this single Review (1,2,3,4,5).
     * @param comment the comment of this single Review.
     * @param username the user who receives this single Review.
     */

    public Review(int rating, String comment, String username, String userviewed) {
        this.rating = rating;
        this.comment = comment;
        this.userviewed = userviewed;
        this.username = username;
        this.id = count++;
    }

    public Review(int rating, String comment, String username, String userviewed, int id) {
        this.rating = rating;
        this.comment = comment;
        this.userviewed = userviewed;
        this.username = username;
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

    public User getUser() {
        return db.findUser(username);
    }

    public User getUserCommented() {
        return db.findUser(userviewed);
    }

    public void setUser(String username) {
        this.username = username;
    }

}
