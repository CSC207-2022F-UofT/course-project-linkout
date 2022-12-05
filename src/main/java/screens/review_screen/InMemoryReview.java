package screens.review_screen;

import entities.Review;
import use_cases.review_use_case.ReviewGateway;

import java.util.HashMap;
import java.util.Map;

public class InMemoryReview implements ReviewGateway {
    final private Map<Integer, Review> reviews = new HashMap<>();

    @Override
    public void saveReview(Review review) {
        System.out.println("Save " + review.getId() + review.getComment());
        reviews.put(review.getId(), review);
    }

    @Override
    public void removeReview(int id, String username){
        System.out.println("Move Success");
        reviews.remove(id);
    }

    @Override
    public Review findReview(int reviewId){
        return reviews.get(reviewId);
    }

    @Override
    public int getLargestReviewId(){
        return 0;
    }

}