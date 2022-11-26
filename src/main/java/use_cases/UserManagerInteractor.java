package use_cases;

import entities.*;

public class UserManagerInteractor implements UserInputBoundary {

    final UserDsGateway userDsGateway;
    final UserOutputBoundary userPresenter;
    final UserFactory userFactory;


    public UserManagerInteractor(UserDsGateway userDsGateway, UserOutputBoundary userPresenter,
                                 UserFactory userFactory){
        this.userDsGateway = userDsGateway;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    public void viewLiked(UserRequestModel userRequestModel) {
        if (userDsGateway.existByName(userRequestModel.getAccName())) {
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareLikedView(user.showLiked());
        }
        userPresenter.prepareFailedView("User does not exist.");
    }

    public void viewAccountStatus(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareAccStatusView(user.showVip(), user.getRestrictedTime());
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    public void showVIPStatus(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareVipStatusView(user.showVip());
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    public void showRestrictedStatus(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareRestrictionStatusView(user.getRestrictedTime());
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    public void changeVIPStatus(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                userPresenter.prepareFailedView("User is already VIP");
            }
            userPresenter.prepareSuccessView(user.setVipStatus(true));
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    public void getRestrictionTime(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            if (user.getRestrictedTime() != 0){
                userPresenter.prepareRestrictionStatusView(user.getRestrictedTime());
            }
            userPresenter.prepareFailedView("User is not restricted");
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    public void setRestrictionTime(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            if (user.getRestrictedTime() != 0){
                userPresenter.prepareFailedView("User already restricted");
            }
            user.countDownRestrictionTime();
            userPresenter.prepareSuccessView(user.setRestrictedTime(userRequestModel.getRestrictionTime()));
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    public void showProfile(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())){
            User user = findUserByName(userRequestModel.getAccName());
            Profile profile = user.displayProfile();
            userPresenter.prepareProfileView(profile);
        }
        userPresenter.prepareFailedView("User does not exist");
    }

    User findUserByName(String accName){
        return this.userDsGateway.findUser(accName);
    }

}
