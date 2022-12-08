package use_cases.user_use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.user_info_screen.UserInfoScreen;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_manager_user_case.UserOutputBoundary;
import use_cases.user_manager_user_case.UserPresenter;
import use_cases.user_manager_user_case.UserRequestModel;

public class RegularUserManagerTest {
    UserGateway userGateway;
    UserOutputBoundary userPresenter;
    UserRequestModel model;

    @BeforeEach
    void setup(){
        this.model = new UserRequestModel("acc1", 0);
        UserInfoScreen userInfoScreen = new UserInfoScreen();
        this.userGateway = new UserGateway(System.getProperty("user.dir"));
        this.userPresenter = new UserPresenter(userInfoScreen);
    }

    @Test
    void
}
