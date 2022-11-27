package use_cases.RecommendUseCase;

import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

public interface RecommendDsGateway {

    List<User> LoadAllUser(String type) throws IOException, InvalidAttributeValueException;

}
