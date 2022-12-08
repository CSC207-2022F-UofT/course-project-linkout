
import entities.RegUserFactory;
import entities.AllUserFactory;
import screens.regularuser_register_screen.*;
import screens.user_login.UserLoginController;
import screens.user_login.UserLoginResponseFormatter;
import screens.user_login.LoginScreen;
import use_cases.regular_user_register_use_case.*;
import use_cases.user_login_use_case.UserLoginDsGateway;
import use_cases.user_login_use_case.UserLoginInputBoundary;
import use_cases.user_login_use_case.UserLoginInteractor;
import use_cases.user_login_use_case.UserLoginPresenter;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidAttributeValueException {

        // Build the main program window
        JFrame application = new JFrame("RegularUserRegister");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Create the parts to plug into the Use Case+Entities enginel

        UserRegisterDsGateway user;
        try {
            user = new UserGateway(System.getProperty("user.dir"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserRegisterPresenter presenter = new UserRegisterResponseFormatter();
        AllUserFactory userFactory = new RegUserFactory();
        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                user, presenter, userFactory);
        UserRegisterController userRegisterController = new UserRegisterController(
                interactor
        );

//         Build the GUI, plugging in the parts
        RegisterScreen registerScreen = new RegisterScreen(userRegisterController);
        screens.add(registerScreen, "welcome");
        cardLayout.show(screens, "register");
        application.pack();
        application.setVisible(true);

        UserLoginPresenter loginpresenter = new UserLoginResponseFormatter();
        UserLoginDsGateway loginuser;
        try {
            loginuser = new UserGateway(System.getProperty("user.dir"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserLoginInputBoundary Logininteractor = new UserLoginInteractor(loginuser, loginpresenter);
        UserLoginController userLoginController = new UserLoginController(
                Logininteractor
        );
//
//        WelcomeScreen welcomeScreen = new WelcomeScreen();
        LoginScreen loginScreen = new LoginScreen(userLoginController);
////        LoggedInScreen loggedInScreen = new LoggedInScreen();
////        screens.add(welcomeScreen, "register");
        loginScreen.pack();
        loginScreen.setVisible(true);
//        screens.add(loginScreen, "login");
//        screens.add(loggedInScreen, "loggedIn");

    }

}