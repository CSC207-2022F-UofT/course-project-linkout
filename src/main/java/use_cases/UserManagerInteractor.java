package use_cases;

import Gateway.DatabaseConnect;
import entities.*;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class UserManagerInteractor implements UserInputBoundary {

    final DatabaseConnect userDsGateway;
    final UserOutputBoundary userPresenter;



    public UserManagerInteractor(DatabaseConnect userDsGateway, UserOutputBoundary userPresenter){
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
            userPresenter.prepareAccStatusView(user.showVip(), user.getRestrictedTime());
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
            if (user.getRestrictedTime() != 0){
                userPresenter.prepareFailedView("User already restricted");
            }else {
                user.countDownRestrictionTime();
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
