package use_cases.regular_user_register_use_case;

import entities.AllUserFactory;
import entities.User;
import entities.UserFactory;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserRegisterInteractor implements UserRegisterInputBoundary {

    final UserRegisterDsGateway userDsGateway;
    final UserRegisterPresenter userPresenter;

    final AllUserFactory regUserFactory;

    public UserRegisterInteractor(UserRegisterDsGateway userRegisterDfGateway,
                                  UserRegisterPresenter userRegisterPresenter,
                                  AllUserFactory regUserFactory) {
        this.userDsGateway = userRegisterDfGateway;
        this.userPresenter = userRegisterPresenter;
        this.regUserFactory = regUserFactory;
    }

    @Override
    public UserRegisterResponseModel create(UserRegisterRequestModel requestModel) throws IOException, InvalidAttributeValueException {
        if (userDsGateway.existsByName(requestModel.getAccountName())) {
            return userPresenter.prepareFailView("User already exists.");
        } else if (!requestModel.getPassword().equals(requestModel.getRepeatPassword())) {
            return userPresenter.prepareFailView("Passwords don't match.");
        }

        User user = regUserFactory.create(requestModel.getPassword(),
                requestModel.getAccountName(), requestModel.getProfile());
        if (!user.passwordIsValid()) {
            return userPresenter.prepareFailView("User password must have more than 3 characters.");
        }

        LocalDateTime now = LocalDateTime.now();
        UserRegisterDsRequestModel userDsModel = new UserRegisterDsRequestModel(user.getAccountName(),
                user.getPassword(), user.displayProfile().getLocation(), user.displayProfile().getGender(),
                user.displayProfile().getAge(), user.displayProfile().getSexuality(),
                user.displayProfile().getHobbies(), user.displayProfile().getHeight(),
                user.displayProfile().getWeight(), user.displayProfile().getContactInformation(),
                user.displayProfile().getSelfDescription(), now);
        userDsGateway.saveUser(userDsModel);

        UserRegisterResponseModel accountResponseModel = new UserRegisterResponseModel(user.getAccountName(),
                now.toString());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}