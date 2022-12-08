package use_cases.user_manager_user_case;


import entities.User;

import entities.VipUser;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_action_use_case.LikesGateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;


public class VIPUserManager extends UserManagerInteractor{
    LikesGateway likesGateway;

    public VIPUserManager(UserGateway userDsGateway, UserOutputBoundary userPresenter){
        super(userDsGateway, userPresenter);
        likesGateway = new LikesGateway(System.getProperty("user.dir"));
    }


    /**
     * @param userRequestModel contains user's information
     * @param invisible whether current user is in invisible mode
     */
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

    /**
     * @param userRequestModel contains the user Information
     */
    public void showLikeMe(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException {
        if (userDsGateway.findUser(userRequestModel.getAccName()) != null) {
            User user = findUserByName(userRequestModel.getAccName());
            if (user.showVip()){
                userPresenter.prepareLikedMeView(likesGateway.findLikedMe(user.getAccountName()));
            }
            else {
                userPresenter.prepareFailedView("Only VIP user can see who liked him/her");
            }
        }else {
            userPresenter.prepareFailedView("User does not exist.");
        }
    }

}
