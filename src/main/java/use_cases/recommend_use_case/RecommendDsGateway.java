package use_cases.recommend_use_case;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public interface RecommendDsGateway {

    String getWorkingDir();

    List<User> LoadAllUser(String type) throws IOException, InvalidAttributeValueException;


    boolean hasLiked(String username) throws IOException, InvalidAttributeValueException;

    void SaveSeen(String username, List<User> usersviewed) throws IOException, InvalidAttributeValueException;
}
