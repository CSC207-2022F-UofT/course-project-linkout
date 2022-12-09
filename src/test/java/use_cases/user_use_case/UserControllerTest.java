package use_cases.user_use_case;

import entities.RegularUser;
import entities.User;
import entities.VipUser;
import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.user_info_screen.UserInfoScreen;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_manager_user_case.*;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserGateway userGateway;
    UserOutputBoundary userPresenter;
    UserRequestModel model;

    UserInputBoundary interactor;

    @BeforeEach
    void setup() {
        this.model = new UserRequestModel("acc1", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
        this.interactor = new VIPUserManager(userGateway, userPresenter);
    }

    @Test
    void viewLiked() {
        try {
            UserController userController = new UserController(interactor);
            userController.viewLiked(model);
        } catch (IOException | InvalidAttributeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void viewAccountStatus() {
        try {
            UserController userController = new UserController(interactor);
            userController.viewAccountStatus(model);
            userPresenter.prepareAccStatusView(userGateway.findUser(model.getAccName()).showVip(),
                    userGateway.findUser(model.getAccName()).getRestrictionDuration());
        } catch (IOException | InvalidAttributeValueException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void showProfile() {
        try {
            UserController userController = new UserController(interactor);
            userController.showProfile(model);
        } catch (IOException | InvalidAttributeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void viewLikedMeVIP() throws IOException, InvalidAttributeValueException {
        User user = userGateway.findUser(model.getAccName());
        if (!user.showVip()) {
            new Fail(new Throwable("failed"));
        }else{
            VipUser vipUser = (VipUser) user;
            assertNotNull(vipUser.showLikedMe());
        }
    }

    @Test
    void displayReview() {
        try {
            UserController userController = new UserController(interactor);
            userController.displayReview(model);
        } catch (IOException | InvalidAttributeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void setInvisibleVisit() throws IOException, InvalidAttributeValueException {
        User user = userGateway.findUser(model.getAccName());
        UserController userController = new UserController(interactor);
        userController.setInvisibleVisit(model, true);
    }

    @Test
    void viewLikedMeVIPTest() throws IOException, InvalidAttributeValueException {
        User user = userGateway.findUser(model.getAccName());
        UserController userController = new UserController(interactor);
        userController.viewLikedMeVIP(model);

    }


    @Test
    void changeVIPStatus() throws IOException, InvalidAttributeValueException {
        this.model = new UserRequestModel("acc5", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
        this.interactor = new VIPUserManager(userGateway, userPresenter);
        User user = userGateway.findUser(model.getAccName());
        UserController userController = new UserController(interactor);
        userController.changeVIPStatus(model);

    }
}
