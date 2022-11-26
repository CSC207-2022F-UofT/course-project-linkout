package use_cases;


import entities.*;

import java.util.Hashtable;
import java.util.List;

public class RegularUserManager extends UserManagerInteractor{

    public RegularUserManager(UserDsGateway userDsGateway, UserOutputBoundary userPresenter,
                              UserFactory userFactory){
        super(userDsGateway, userPresenter, userFactory);
    }

    public void upgrade(UserRequestModel username){
        if (userDsGateway.existByName(username.getAccName())) {
            User user = super.findUserByName(username.getAccName());
            if (!user.showVip()){
                userDsGateway.upgrade(username.getAccName(), true);
                userPresenter.prepareSuccessView(true);
            }
            userPresenter.prepareFailedView("User cannot be upgraded");
        }
        userPresenter.prepareFailedView("User does not exist.");
    }
}
