package useCases.review_use_case;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRequestModelTest {

    @Test
    void getRating() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        assertTrue(request.getRating() == 1);
    }

    @Test
    void setRating() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        request.setRating(2);
        assertTrue(request.getRating() == 2);
    }

    @Test
    void getComment() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        assertTrue(request.getComment().equals("good guy"));
    }

    @Test
    void setComment() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        request.setComment("bad guy");
        assertTrue(request.getComment().equals("bad guy"));
    }

    @Test
    void getWriter() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        assertTrue(request.getWriter().equals("Alice"));
    }

    @Test
    void setWriter() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        request.setWriter("Mary");
        assertTrue(request.getWriter().equals("Mary"));
    }

    @Test
    void getReceiver() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        assertTrue(request.getReceiver().equals("Bob"));
    }

    @Test
    void setReceiver() {
        ReviewRequestModel request = new ReviewRequestModel(1,"good guy", "Alice", "Bob");
        request.setReceiver("Mary");
        assertTrue(request.getReceiver().equals("Mary"));
    }

    @Test
    void isComplete() {
        ReviewRequestModel request1 = new ReviewRequestModel(0,"good guy", "Alice", "Bob");
        ReviewRequestModel request2 = new ReviewRequestModel(1,null, "Alice", "Bob");
        ReviewRequestModel request3 = new ReviewRequestModel(1,"good guy", null, "Bob");
        ReviewRequestModel request4 = new ReviewRequestModel(1,"good guy", "Alice", null);
        assertFalse(request1.isComplete());
        assertFalse(request2.isComplete());
        assertFalse(request3.isComplete());
        assertFalse(request4.isComplete());
    }
}