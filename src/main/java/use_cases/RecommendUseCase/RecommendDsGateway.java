package use_cases.RecommendUseCase;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public interface RecommendDsGateway {

    String getWorkingDir();

    List<User> LoadAllUser(String type) throws IOException, InvalidAttributeValueException;

    void SaveSeen(String username, List<String> usersviewed) throws IOException;
}
