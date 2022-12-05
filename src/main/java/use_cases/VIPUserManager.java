package use_cases;

import Gateway.DatabaseConnect;
import entities.User;
import entities.UserFactory;
import entities.VipUser;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VIPUserManager extends UserManagerInteractor{


    public VIPUserManager(DatabaseConnect userDsGateway, UserOutputBoundary userPresenter){
        super(userDsGateway, userPresenter);
    }

    public void hideReview(UserRequestModel userRequestModel, Integer review_ids) throws IOException, InvalidAttributeValueException {
        if (userDsGateway.findUser(userRequestModel.getAccName()) != null) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                VipUser vUser = (VipUser) user;
                userDsGateway.deleteReview(review_ids);
                userPresenter.prepareSuccessView(vUser.hideReview(review_ids));
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can hide review");
            }
        }else {
            userPresenter.prepareFailedView("User does not exist.");
        }
    }

    public void invisibleVisit(UserRequestModel userRequestModel, boolean invisible) throws IOException, InvalidAttributeValueException {
        if (userDsGateway.findUser(userRequestModel.getAccName()) != null) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                VipUser vUser = (VipUser) user;
                if(vUser.getInvisibleStatus()){
                    userPresenter.prepareFailedView("Already in invisible Mode");
                }else {
                    userPresenter.prepareSuccessView(vUser.setInvisible(invisible));
                }
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can set invisible");
            }
        }else {
            userPresenter.prepareFailedView("User does not exist.");
        }
    }

    public void showLikeMe(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (userDsGateway.findUser(userRequestModel.getAccName()) != null) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                userPresenter.prepareLikedMeView(userDsGateway.findLikedMe(user.getAccountName()));
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can see who liked him/her");
            }
        }else {
            userPresenter.prepareFailedView("User does not exist.");
        }
    }

}
