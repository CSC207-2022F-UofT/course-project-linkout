package use_cases.user_use_case;


import entities.Profile;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.user_info_screen.UserInfoScreen;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_manager_user_case.UserOutputBoundary;
import use_cases.user_manager_user_case.UserPresenter;
import use_cases.user_manager_user_case.UserRequestModel;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerInteractorTest {

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
    void viewLiked() {
        try {
            User testUser = userGateway.findUser(model.getAccName());
            assertNotNull(testUser.showLiked());
        } catch (IOException | InvalidAttributeValueException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void viewAccountStatus() {
        try {
            User testUser = userGateway.findUser(model.getAccName());
            assertTrue(testUser.showVip());
            assertTrue(testUser.isRestricted());
            assertEquals(testUser.getRestrictionDuration(), 0.0);
        } catch (IOException | InvalidAttributeValueException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void showReview() {
        try {
            User testUser = userGateway.findUser(model.getAccName());
            assertNotNull(testUser.getReviews());
        } catch (IOException | InvalidAttributeValueException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void showProfile() {
        try {
            User testUser = userGateway.findUser(model.getAccName());
            assertNotNull(testUser.displayProfile());
        } catch (IOException | InvalidAttributeValueException e){
            System.out.println(e.getMessage());
        }
    }
}