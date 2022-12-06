package use_cases.search_use_case.search_screen;
import entities.User;

import screens.review_screen.IReviewViewImplementation;
import screens.review_screen.ReviewCreationScreen;
import screens.user_info_screen.UserInfoScreen;
import use_cases.recommend_use_case.*;
import use_cases.record_review_use_case.*;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.review_use_case.*;
import use_cases.search_use_case.*;
import use_cases.user_action_use_case.*;

import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SearchRecommendScreen extends JFrame {

    private JTextField txtKeyword;
    private JTextField username;

    private JTable table;

    private static UserActController likeController;
    private static UserActPresenter likePresenter;

    private static SearchController searchController;

    private static RecommendController recommendController;

    private ReviewController reviewController;

    private static RecordReportController reportController;




    /**
     * Launch the SearchMatch UI window.
     */

    public static void main(String[] args) throws IOException {
        //search function
        SearchInputBoundaryImplementation searchInput = new SearchInputBoundaryImplementation();
        SearchController searchController = new SearchController(searchInput);


        //like function
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        UserActDsGateway likeGateway = new LikesGateway(System.getProperty("user.dir"), userGateway);
        UserActPresenterInterface likePresenter = new UserActPresenter();
        UserActInputBoundary userActInteractor = new UserActInteractor(likeGateway, likePresenter);
        UserActController userActController = new UserActController(userActInteractor);

        //review function
        IReviewViewImplementation viewReview = new IReviewViewImplementation();
        ReviewPresenter reviewPresenter = new ReviewPresenter(viewReview);
        ReviewGatewayImplementation reviewsGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        //UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        ReviewInputBoundary reviewInteractor = new ReviewInteractor(reviewPresenter, reviewsGateway, userGateway);
        ReviewController reviewController = new ReviewController(reviewInteractor);


//        //report function (stilmouseRl need recordReportGateway class)
//        RecordReportOutputData recordReportOD = new RecordReportOutputData();
//        RecordReportResultFrame viewReport = new RecordReportResultFrame();
//        RecordReportPresenter reportPresenter = new RecordReportPresenter(recordReportOD, viewReport);
//
//        RecordReportGateway recordReportGateway = new RecordReportGateway(System.getProperty("user.dir"));
//
//        RecordReportInteractor reportInteractor = new RecordReportInteractor(recordReportOD, reportPresenter, recordReportGateway);


//        //recommend function
//        RecommendController recommendController = new RecommendController();

        SearchRecommendScreen frame = new SearchRecommendScreen(searchController, userActController,
                (UserActPresenter) likePresenter, reviewController);
        frame.setVisible(true);
    }


    public SearchRecommendScreen(SearchController searchcontroller, UserActController likecontroller,
                                   UserActPresenter likepresenter, ReviewController reviewcontroller) {

        likeController = likecontroller;
        likePresenter = likepresenter;
//        recommendController = recommendcontroller;
        searchController = searchcontroller;
        reviewController = reviewcontroller;


        // Build the empty frame for UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 1400, 342);

        setTitle("Search for Match");

        getContentPane().setLayout(null);

        // Create the ScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(65, 72, 1300, 159);
        getContentPane().add(scrollPane);

        // Create the Label "Enter Your Username" beside the username entered field
        JLabel lblUsername = new JLabel("Enter Your Username ");

        lblUsername.setBounds(55, 27, 144, 14);
        getContentPane().add(lblUsername);

        // Create a JTextField for Username Inputs
        username = new JTextField();
        username.setBounds(195, 24, 160, 20);

        getContentPane().add(username);

        username.setColumns(10);


        // Create the Table for display the results
        table = new JTable();
        scrollPane.setViewportView(table);

        // Create the Label "Find The One : )" beside the search field
        JLabel lblSearch = new JLabel("Find The One :)");

        lblSearch.setBounds(85, 47, 124, 14);

        getContentPane().add(lblSearch);

        // Create a JTextField for Keyword Inputs
        txtKeyword = new JTextField();
        txtKeyword.setBounds(195, 44, 160, 20);

        getContentPane().add(txtKeyword);

        txtKeyword.setColumns(10);


        // Create the Button Search
        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg) {
                // Binding Data.
                GenerateSearchResult();
            }
        });

        btnSearch.setBounds(365, 44, 79, 23);
        getContentPane().add(btnSearch);
        // Bind and PopulateData
        GenerateSearchResult();

//      Create a Button Recommend(clean up the current table(either search or
//      recommend results and display a new recommendation results)
        JButton btnRecommend = new JButton("Recommend");

        btnRecommend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg){
//                GenerateRecommendResult();
                JOptionPane.showMessageDialog(getContentPane(),"Need to add");

            }
        });
        btnRecommend.setBounds(365, 20, 119, 23);
        getContentPane().add(btnRecommend);
//        GenerateRecommendResult();
    }

    private void GenerateSearchResult() {

        // Make the table in UI empty -- Initialization
        table.setModel(new DefaultTableModel());

        // Model for Table by adding column titles
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addColumn("Age");

        model.addColumn("Gender");

        model.addColumn("Location");

        model.addColumn("Sexuality");

        model.addColumn("Height");

        model.addColumn("SelfDescription");

        model.addColumn("Weight");

        model.addColumn("Hobbies");

        model.addColumn("Username");

        model.addColumn("Like");

        model.addColumn("Review");

        model.addColumn("Report");

        model.addColumn("Profile");

        try {
            // Storing the keywords and username entered as a String
            String searchTexts = txtKeyword.getText();
            String userName = username.getText();

            SearchResponseModel responseModel = searchController.searchSheet(searchTexts, userName);

            // Get the list of 20 matched users
            List<User> matchedSearch = responseModel.getTwentyMatchedUsers();

            // Iterating the list of matched users and updating the models with these matched users and
            // their corresponding profile
            for (int i = 0; i < matchedSearch.size(); i++) {

                model.addRow(new Object[0]);

                User currentUser = matchedSearch.get(i);

                model.setValueAt(currentUser.displayProfile().getAge(), i, 0);

                model.setValueAt(currentUser.displayProfile().getGender(), i, 1);

                model.setValueAt(currentUser.displayProfile().getLocation(), i, 2);

                model.setValueAt(currentUser.displayProfile().getSexuality(), i, 3);

                model.setValueAt(currentUser.displayProfile().getHeight(), i, 4);

                model.setValueAt(currentUser.displayProfile().getSelfDescription(), i, 5);

                model.setValueAt(currentUser.displayProfile().getWeight(), i, 6);

                model.setValueAt(currentUser.displayProfile().getHobbies(), i, 7);

                model.setValueAt(currentUser.getAccountName(), i, 8);

                // Create the Button Like
                Action likeAction = new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        int currRow = Integer.valueOf( e.getActionCommand() );
                        String targetName = (String) table.getModel().getValueAt(currRow, 8);
                        likeController.like(userName,targetName);
                        String message = likePresenter.prepareSuccessView(targetName);
                        //String message = likeController.like(userName,targetName);
                        JOptionPane.showMessageDialog(getContentPane(), message);
                    }
                };
                ButtonColumnLike likeButton = new ButtonColumnLike(table, likeAction, 9);


                // Create the Button Review
                Action reviewAction = new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        ReviewCreationScreen reviewCreationScreen = new ReviewCreationScreen(reviewController);
                        reviewCreationScreen.setVisible(true);
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                };
                ButtonColumnReview reviewButton = new ButtonColumnReview(table, reviewAction, 10);

                // Create the Button Profile
                Action profileAction = new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        UserInfoScreen userinfoScreen = new UserInfoScreen();
                        userinfoScreen.setVisible(true);
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                };
                ButtonColumnProfile profileButton = new ButtonColumnProfile(table, profileAction, 12);


//                // Create the Button Report
//                Action reportAction = new AbstractAction()
//                {
//                    @Override
//                    public void actionPerformed(ActionEvent e)
//                    {
//                       //report action defines here
//                    }
//                };
//                ButtonColumnReview reportButton = new ButtonColumnReview(table, reportAction, 11);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void GenerateRecommendResult() {
//
//        // Make the table in UI empty -- Initialization
//        table.setModel(new DefaultTableModel());
//
//        // Model for Table by adding column titles
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//        model.addColumn("Age");
//
//        model.addColumn("Gender");
//
//        model.addColumn("Location");
//
//        model.addColumn("Sexuality");
//
//        model.addColumn("Height");
//
//        model.addColumn("SelfDescription");
//
//        model.addColumn("Weight");
//
//        model.addColumn("Hobbies");
//
//        model.addColumn("Username");
//
//        model.addColumn("Like");
//
//        model.addColumn("Review");
//
//        model.addColumn("Report");
//
//        model.addColumn("Profile");
//
//        try {
//            // Storing the keywords and username entered as a String
//            String userName = username.getText();
//
//            RecommendGateway recommendGateway = new RecommendGateway(System.getProperty("user.dir"));
//            RecommendInteractor recommendInteractor = new RecommendInteractor(recommendGateway);
//
//            RecommendController recommendController = new RecommendController(recommendInteractor);
//
//            RecommendResponseModel responseModel = recommendController.recommend(userName);
//
//            // Get the list of 20 matched users
//            List<User> recommendUsers = responseModel.getAllUsers();
//
//            // Iterating the list of matched users and updating the models with these matched users and
//            // their corresponding profile
//            for (int i = 0; i < recommendUsers.size(); i++) {
//
//                model.addRow(new Object[0]);
//
//                User currentUser = recommendUsers.get(i);
//
//                model.setValueAt(currentUser.displayProfile().getAge(), i, 0);
//
//                model.setValueAt(currentUser.displayProfile().getGender(), i, 1);
//
//                model.setValueAt(currentUser.displayProfile().getLocation(), i, 2);
//
//                model.setValueAt(currentUser.displayProfile().getSexuality(), i, 3);
//
//                model.setValueAt(currentUser.displayProfile().getHeight(), i, 4);
//
//                model.setValueAt(currentUser.displayProfile().getSelfDescription(), i, 5);
//
//                model.setValueAt(currentUser.displayProfile().getWeight(), i, 6);
//
//                model.setValueAt(currentUser.displayProfile().getHobbies(), i, 7);
//
//                model.setValueAt(currentUser.getAccountName(), i, 8);
//
//                // Create the Button Like
//                Action likeAction = new AbstractAction()
//                {
//                    @Override
//                    public void actionPerformed(ActionEvent e)
//                    {
//                        int currRow = Integer.valueOf( e.getActionCommand() );
//                        String targetName = (String) table.getModel().getValueAt(currRow, 8);
//                        String message = likePresenter.prepareSuccessView(targetName);
//                        JOptionPane.showMessageDialog(getContentPane(),message);
//                        likeController.like(userName,targetName);
//
//                    }
//                };
//                ButtonColumnLike likeButton = new ButtonColumnLike(table, likeAction, 9);
//
//
//
//                // Create the Button Profile
//                Action reviewAction = new AbstractAction()
//                {
//                    @Override
//                    public void actionPerformed(ActionEvent e)
//                    {
//                        ReviewCreationScreen reviewCreationScreen = new ReviewCreationScreen(reviewController);
//                        reviewCreationScreen.setVisible(true);
//                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    }
//                };
//                ButtonColumnReview reviewButton = new ButtonColumnReview(table, reviewAction, 10);
//
////                // Create the Button Profile
////                Action profileAction = new AbstractAction()
////                {
////                    @Override
////                    public void actionPerformed(ActionEvent e)
////                    {
////                        ReviewCreationScreen reviewCreationScreen = new ReviewCreationScreen(profileController);
////                        reviewCreationScreen.setVisible(true);
////                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////                    }
////                };
////                ButtonColumnProfile profileButton = new ButtonColumnProfile(table, profileAction, 12);
//
//
//
////                // Create the Button Report
////                Action reportAction = new AbstractAction()
////                {
////                    @Override
////                    public void actionPerformed(ActionEvent e)
////                    {
////                        ReviewCreationScreen reviewCreationScreen = new ReviewCreationScreen(reportController);
////                        reviewCreationScreen.setVisible(true);
////                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////                    }
////                };
////                ButtonColumnReview reportButton = new ButtonColumnReview(table, reportAction, 11);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
