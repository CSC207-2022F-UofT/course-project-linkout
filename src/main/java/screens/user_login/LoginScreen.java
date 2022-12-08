package screens.user_login;

import screens.record_report.RecordReportResultFrame;
import screens.review_screen.IReviewView;
import screens.review_screen.ReviewCreationSuccessScreen;
import screens.search_screen.SearchRecommendScreen;
import use_cases.record_report_use_case.*;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.review_use_case.*;
import use_cases.search_use_case.SearchController;
import use_cases.search_use_case.SearchDSGateway;
import use_cases.search_use_case.SearchGateway;
import use_cases.search_use_case.SearchInteractor;
import use_cases.user_action_use_case.*;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Frameworks/Drivers layer

public class LoginScreen extends JFrame implements ActionListener {
    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);
    UserLoginController userLoginController;


    /**
     * A window with a title and a JButton.
     */
    public LoginScreen(UserLoginController userLoginController) {
        this.userLoginController = userLoginController;

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), password);

        JButton logIn = new JButton("Log in");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(cancel);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(usernameInfo);
        main.add(passwordInfo);
        main.add(buttons);
        this.setContentPane(main);

        this.pack();
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

        System.out.println("Click " + evt.getActionCommand());
        try{
            userLoginController.create(username.getText(),
                    String.valueOf(password.getPassword()));

            this.setVisible(false);
            JOptionPane.showMessageDialog(this, username.getText() + " log in.");

            //Pop up search UI
            //search function
            SearchDSGateway searchDSGateway = new SearchGateway(System.getProperty("user.dir"));
            SearchInteractor searchInteractor = new SearchInteractor(searchDSGateway);
            SearchController searchController = new SearchController(searchInteractor);

            //like function
            UserActDsGateway userActDsGateway = new LikesGateway(System.getProperty("user.dir"));
            UserActPresenterInterface userActPresenter = new UserActPresenter();
            UserActInputBoundary userActInteractor = new UserActInteractor(userActDsGateway, userActPresenter);
            UserActController userActController = new UserActController(userActInteractor);

            //review function
            IReviewView screen = new ReviewCreationSuccessScreen();
            ReviewPresenter reviewPresenter = new ReviewPresenter(screen);
            ReviewGatewayImplementation reviewsGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
            UserGateway userGateways = new UserGateway(System.getProperty("user.dir"));
            ReviewInputBoundary reviewInteractor = new ReviewInteractor(reviewPresenter, reviewsGateway, userGateways);
            ReviewController reviewController = new ReviewController(reviewInteractor);

            //report
            RecordReportOutputData recordReportOD = new RecordReportOutputData();
            RecordReportResultFrame viewReport = new RecordReportResultFrame();
            UserGateway recordReportGateway = new UserGateway(System.getProperty("user.dir"));
            RecordReportPresenter reportPresenter = new RecordReportPresenter(recordReportOD, viewReport);
            ReportDatabaseGateway recordReportDatabaseGateway = new ReportDatabase(System.getProperty("user.dir"));
            RecordReportInteractor reportInteractor = new RecordReportInteractor(reportPresenter, recordReportGateway,
                    recordReportDatabaseGateway, "Admin");
            RecordReportController recordReportController = new RecordReportController(reportInteractor);


            SearchRecommendScreen frame = new SearchRecommendScreen(searchController, userActController,
                    reviewController, recordReportController);
            frame.setVisible(true);

        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }
}