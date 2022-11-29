package use_cases.review_use_case;

public class ReviewCreationFailed extends RuntimeException{
    public ReviewCreationFailed(String error) {
        super(error);
    }
}
