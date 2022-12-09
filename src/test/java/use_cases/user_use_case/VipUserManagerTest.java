package use_cases.user_use_case;

import org.junit.jupiter.api.Test;
import screens.user_info_screen.UserInfoScreen;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_manager_user_case.UserOutputBoundary;
import use_cases.user_manager_user_case.UserPresenter;
import use_cases.user_manager_user_case.UserRequestModel;
import use_cases.user_manager_user_case.VIPUserManager;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class VipUserManagerTest {
    UserGateway userGateway;
    UserOutputBoundary userPresenter;
    UserRequestModel model;

    @Test
    void testInvisible() throws IOException, InvalidAttributeValueException {
        this.model = new UserRequestModel("acc1", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
        VIPUserManager vipUserManager = new VIPUserManager(userGateway, userPresenter);
        //vipUserManager.invisibleVisit(model, true);
    }

    @Test
    void testShowLikeMe() throws IOException, InvalidAttributeValueException {
        this.model = new UserRequestModel("acc1", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
        VIPUserManager vipUserManager = new VIPUserManager(userGateway, userPresenter);
        //vipUserManager.showLikeMe(model);
    }
}
