package use_cases.user_action_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

// use case layer
public interface UserActInputBoundary {
    String like(UserActInputData inputData) throws IOException, InvalidAttributeValueException;

}