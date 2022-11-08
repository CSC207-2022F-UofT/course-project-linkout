package controller;

public class IncompleteReviewException extends Exception {
    public IncompleteReviewException() {
        super("This review is not complete.");
    }
}
