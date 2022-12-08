import entities.AllUserFactory;
import entities.RegUserFactory;
import screens.regularuser_register_screen.RegisterScreen;
import screens.regularuser_register_screen.UserRegisterController;
import screens.regularuser_register_screen.UserRegisterResponseFormatter;
import screens.user_login.LoginScreen;
import screens.user_login.UserLoginController;
import screens.user_login.UserLoginResponseFormatter;
import use_cases.regular_user_register_use_case.*;
import use_cases.user_login_use_case.UserLoginDsGateway;
import use_cases.user_login_use_case.UserLoginInputBoundary;
import use_cases.user_login_use_case.UserLoginInteractor;
import use_cases.user_login_use_case.UserLoginPresenter;
import javax.swing.*;
import java.awt.*;



public class Main {


    public static void main(String[] args) {

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

        LoginScreen loginScreen = new LoginScreen(userLoginController);

        loginScreen.pack();
        loginScreen.setVisible(true);
    }
}




