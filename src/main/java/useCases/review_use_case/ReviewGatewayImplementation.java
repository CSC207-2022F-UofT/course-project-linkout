package useCases.review_use_case;

import entities.Review;
import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewGatewayImplementation implements ReviewGateway{
    private String filename;

    public ReviewGatewayImplementation(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveReview(List<Review> reviews) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Review review: reviews){
                writer.write(review.toSavableFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveReview(int id, User user) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            Review review;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if (id == Integer.valueOf(data[2])){
                    //TODO: Implement the method
                };
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void softdeleteReview(int id, User user) {
        //TODO: Implement the method

    }

    @Override
    public List<Review> getReviews() {
        List<Review> reviews = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            Review review;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                User user;
                //TODO: initialize the user
                review = new Review(Integer.valueOf(data[0]), data[1], user);
                review.setId(Integer.valueOf(data[2]));
                reviews.add(review);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;

    }
}
