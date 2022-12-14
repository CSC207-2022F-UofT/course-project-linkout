package screens.regularuser_register_screen;

import use_cases.regular_user_register_use_case.UserRegisterDsGateway;
import use_cases.regular_user_register_use_case.UserRegisterDsRequestModel;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUser implements UserRegisterDsGateway {

    final private Map<String, UserRegisterDsRequestModel> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param requestModel the data to save
     */
    @Override
    public void saveUser(UserRegisterDsRequestModel requestModel) {
        System.out.println("Save " + requestModel.getName());
        users.put(requestModel.getName(), requestModel);
    }
}
