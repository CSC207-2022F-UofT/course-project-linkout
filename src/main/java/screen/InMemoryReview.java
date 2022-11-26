package screen;

import entities.Review;
import entities.User;
import useCases.review_use_case.ReviewGateway;

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
    public void moveReview(int id, User user) {

        System.out.println("Move Success");
        reviews.remove(id);
    }

    @Override
    public void softdeleteReview(int id, User user) {
        System.out.println("Hide Success");
        reviews.remove(id);

    }
}
