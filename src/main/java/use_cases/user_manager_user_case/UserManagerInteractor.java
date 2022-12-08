package use_cases.user_manager_user_case;


import entities.*;
import use_cases.regular_user_register_use_case.UserGateway;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;


public class UserManagerInteractor implements UserInputBoundary {

    final UserGateway userDsGateway;
    final UserOutputBoundary userPresenter;



    public UserManagerInteractor(UserGateway userDsGateway, UserOutputBoundary userPresenter){
        this.userDsGateway = userDsGateway;
        this.userPresenter = userPresenter;
    }

    public void viewLiked(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (findUserByName(userRequestModel.getAccName()) != null) {
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareLikedView(user.showLiked());
        }else {
            userPresenter.prepareFailedView("User does not exist");
        }
    }

    public void viewAccountStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (findUserByName(userRequestModel.getAccName()) != null){
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareAccStatusView(user.showVip(), user.getRestrictionDuration());
        }else {
            userPresenter.prepareFailedView("User does not exist");
        }
    }



    public void showReview(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (findUserByName(userRequestModel.getAccName()) != null){
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareReviewView(user.getReviews());
        }else {
            userPresenter.prepareFailedView("User does not exist");
        }
    }





    public void setRestrictionTime(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (findUserByName(userRequestModel.getAccName()) != null){
            User user = findUserByName(userRequestModel.getAccName());
            if (user.getRestrictionDuration() != 0){
                userPresenter.prepareFailedView("User already restricted");
            }else {
                userPresenter.prepareSuccessView(user.setRestrictedTime(userRequestModel.getRestrictionTime()));
            }
        }else {
            userPresenter.prepareFailedView("User does not exist");
        }
    }

    public void showProfile(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (findUserByName(userRequestModel.getAccName()) != null){
            User user = findUserByName(userRequestModel.getAccName());
            userPresenter.prepareProfileView(user.displayProfile());
        }else {
            userPresenter.prepareFailedView("User does not exist");
        }
    }

    User findUserByName(String accName) throws IOException, InvalidAttributeValueException {
        return this.userDsGateway.findUser(accName);
    }

}