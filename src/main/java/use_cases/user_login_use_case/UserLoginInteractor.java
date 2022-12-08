package use_cases.user_login_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.time.LocalDateTime;


public class UserLoginInteractor implements UserLoginInputBoundary {

    final UserLoginDsGateway userLoginDsGateway;
    final UserLoginPresenter userLoginPresenter;

    public UserLoginInteractor(UserLoginDsGateway userLoginDsGateway, UserLoginPresenter userLoginPresenter) {
        this.userLoginDsGateway = userLoginDsGateway;
        this.userLoginPresenter = userLoginPresenter;
    }


    @Override
    public UserLoginResponseModel create(UserLoginRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        if (userLoginDsGateway.NotExist(requestModel.getUsername())) {
            return userLoginPresenter.LoginFailView("User does not exists.");
        } else if(!userLoginDsGateway.MatchingNameAndPassword(requestModel.getUsername(), requestModel.getPassword())) {
            return userLoginPresenter.LoginFailView("Passwords don't match.");
        }
        LocalDateTime now = LocalDateTime.now();
        UserLoginResponseModel accountResponseModel = new UserLoginResponseModel(requestModel.getUsername(),
                now.toString());
        return userLoginPresenter.LoginSuccessView(accountResponseModel);
    }
}
