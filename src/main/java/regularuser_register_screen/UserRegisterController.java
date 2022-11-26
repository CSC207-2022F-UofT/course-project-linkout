package regularuser_register_screen;

import user_register_use_case.UserRegisterInputBoundary;
import user_register_use_case.UserRegisterRequestModel;
import user_register_use_case.UserRegisterResponseModel;

// Interface adapters layer

public class UserRegisterController {

    final UserRegisterInputBoundary userInput;

    public UserRegisterController(UserRegisterInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    UserRegisterResponseModel create(String username, String password1, String password2,
                                     String location, String gender, String age, String sexuality,
                                     String hobbies, String height, String weight, String contactInformation,
                                     String selfDescription) {
        UserRegisterRequestModel requestModel = new UserRegisterRequestModel(
                username, password1, password2, location, gender, age, sexuality, hobbies, height, weight,
                contactInformation, selfDescription);

        return userInput.create(requestModel);
    }
}
