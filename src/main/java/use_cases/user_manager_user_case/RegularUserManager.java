package use_cases.user_manager_user_case;


import entities.*;
import use_cases.regular_user_register_use_case.UserGateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;


public class RegularUserManager extends UserManagerInteractor {

    public RegularUserManager(UserGateway userDsGateway, UserOutputBoundary userPresenter){
        super(userDsGateway, userPresenter);
    }

    public void upgrade(UserRequestModel username) throws IOException, InvalidAttributeValueException {
        if (userDsGateway.findUser(username.getAccName()) != null) {
            User user = super.findUserByName(username.getAccName());
            if (!user.showVip()){
                userPresenter.prepareSuccessView(userDsGateway.upgrade(username.getAccName(), true));
            }else {
                userPresenter.prepareFailedView("User cannot be upgraded");
            }
        }else {
            userPresenter.prepareFailedView("User does not exist.");
        }
    }
}
