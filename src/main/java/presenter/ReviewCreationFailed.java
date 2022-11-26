package presenter;

public class ReviewCreationFailed extends RuntimeException{
    public ReviewCreationFailed(String error) {
        super(error);
    }
}
