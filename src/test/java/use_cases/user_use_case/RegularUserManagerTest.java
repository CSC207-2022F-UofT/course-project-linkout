package use_cases.user_use_case;


import entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import screens.user_info_screen.UserInfoScreen;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_manager_user_case.RegularUserManager;
import use_cases.user_manager_user_case.UserOutputBoundary;
import use_cases.user_manager_user_case.UserPresenter;
import use_cases.user_manager_user_case.UserRequestModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class RegularUserManagerTest {
    UserGateway userGateway;
    UserOutputBoundary userPresenter;
    UserRequestModel model;


    @Test
    void testUpgrade() throws IOException, InvalidAttributeValueException {
        this.model = new UserRequestModel("acc3", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
        RegularUserManager regularUserManager = new RegularUserManager(userGateway, userPresenter);
        regularUserManager.upgrade(model);
        User user = userGateway.findUser(model.getAccName());
        assertTrue(user.showVip());

    }
    @Test
    void testUpgradeNotVIP() throws IOException, InvalidAttributeValueException {
        this.model = new UserRequestModel("acc1", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
        RegularUserManager regularUserManager = new RegularUserManager(userGateway, userPresenter);
        regularUserManager.upgrade(model);
        User user = userGateway.findUser(model.getAccName());
        assertTrue(user.showVip());

    }
}
