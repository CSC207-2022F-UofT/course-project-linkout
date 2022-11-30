package use_cases.review_use_case;

public class IncompleteReviewException extends Exception{
    public IncompleteReviewException() {
        super("This review is not complete.");
    }
}
