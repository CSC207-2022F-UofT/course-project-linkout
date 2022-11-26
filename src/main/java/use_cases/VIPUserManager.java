package use_cases;

import entities.User;
import entities.UserFactory;
import entities.VipUser;

import java.util.ArrayList;
import java.util.List;

public class VIPUserManager extends UserManagerInteractor{


    public VIPUserManager(UserDsGateway userDsGateway, UserOutputBoundary userPresenter,
                          UserFactory userFactory){
        super(userDsGateway, userPresenter, userFactory);
    }

    public void hideReview(UserRequestModel userRequestModel, ArrayList<Integer> review_ids){
        if (userDsGateway.existByName(userRequestModel.getAccName())) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                VipUser vUser = (VipUser) user;
                userPresenter.prepareSuccessView(vUser.hideReview(review_ids));
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can hide review");
            }
        }
        userPresenter.prepareFailedView("User does not exist.");
    }

    public void invisibleVisit(UserRequestModel userRequestModel, boolean invisible){
        if (userDsGateway.existByName(userRequestModel.getAccName())) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                VipUser vUser = (VipUser) user;
                userPresenter.prepareSuccessView(vUser.setInvisible(invisible));
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can set invisible");
            }
        }
        userPresenter.prepareFailedView("User does not exist.");
    }

    public void showLikeMe(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                VipUser vUser = (VipUser) user;
                userPresenter.prepareLikedMeView(vUser.showLikedMe());
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can see who liked him/her");
            }
        }
        userPresenter.prepareFailedView("User does not exist.");
    }

    public void showVisitor(UserRequestModel userRequestModel){
        if (userDsGateway.existByName(userRequestModel.getAccName())) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                VipUser vUser = (VipUser) user;
                userPresenter.prepareVisitorView(vUser.showVisitor());
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can see visitors");
            }
        }
        userPresenter.prepareFailedView("User does not exist.");
    }
}
