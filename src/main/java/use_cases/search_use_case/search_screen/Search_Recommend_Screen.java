package use_cases.search_use_case.search_screen;

import controller.RecommendController;
import controller.ReviewController;
import entities.User;
import presenter.ReviewPresenter;
import screens.review_screen.ReviewCreationScreen;
import use_cases.review_use_case.ReviewInputBoundary;
import use_cases.review_use_case.ReviewInputBoundaryImplementation;
import use_cases.search_use_case.*;
import use_cases.user_action_use_case.*;
import use_cases.RecommendUseCase.RecommendResponseModel;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;


public class Search_Recommend_Screen extends JFrame {

    private JTextField txtKeyword;
    private JTextField username;

    private JTable table;

    private static UserActController likeController;
    private static UserActPresenter likePresenter;

    private static SearchController searchController;

    private static RecommendController recommendController;

    private static ReviewController reviewController;

    private static ReviewPresenter reviewPresenter;

    private static ReviewCreationScreen reviewCreationScreen;

    /**
     * Launch the SearchMatch UI window.
     */

    public static void main(String[] args){
        //search function
        SearchInputBoundaryImplementation searchInput = new SearchInputBoundaryImplementation();
        SearchController searchController = new SearchController(searchInput);

        //like function
        UserActInputBoundary userInputControler = new UserActInputBoundaryImplementation();
        UserActController likeController = new UserActController(userInputControler);
        UserActPresenter likePresenter = new UserActPresenter();

        //review function
        ReviewInputBoundary reviewInputBoundary = new ReviewInputBoundaryImplementation();
        ReviewController reviewController = new ReviewController(reviewInputBoundary);


//        //recommend function
//        RecommendController recommendController = new RecommendController();

        Search_Recommend_Screen frame = new Search_Recommend_Screen(searchController, likeController, likePresenter,
                 reviewController);
        frame.setVisible(true);

    }


    public Search_Recommend_Screen(SearchController searchcontroller, UserActController likecontroller,
                                   UserActPresenter likepresenter, ReviewController reviewcontroller) {

        likeController = likecontroller;
        likePresenter = likepresenter;
//        recommendController = recommendcontroller;
        searchController = searchcontroller;
        reviewController = reviewcontroller;

        // Build the empty frame for UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 680, 342);

        setTitle("Search for Match");

        getContentPane().setLayout(null);

        // Create the ScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(65, 72, 550, 159);
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

//      Create a Button Refresh(clean up the current table(either search or
//      recommend results and display a new recommendation results)
        JButton btnRefresh = new JButton("Refresh");

//        btnRefresh.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent arg){
//                GenerateRecommendResult();
//            }
//        });
//        btnRefresh.setBounds(465, 44, 79, 23);
//        getContentPane().add(btnRefresh);
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
                    public void actionPerformed(ActionEvent e)
                    {
                        int currRow = Integer.valueOf( e.getActionCommand() );
                        String targetName = (String) table.getModel().getValueAt(currRow, 8);
                        likeController.like(userName,targetName);
                        String message = likePresenter.prepareSuccessView(targetName);
                        JOptionPane.showMessageDialog(getContentPane(),message);
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



//                // Create the Button Review
//                JButton ReportButton = new JButton("Report");
//                LikeButton.addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent arg) {
//                        String targetName = currentUser.getAccountName();
////                        likeController.like(userName,targetName);
//                        JOptionPane.showMessageDialog(getContentPane(),"You successfully reported" + targetName);
//                    }
//                });
//
//                model.setValueAt(ReportButton, i, 11);
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
//        try {
//            // Storing the keywords and username entered as a String
//            String userName = username.getText();
//
//            RecommendResponseModel responseModel = recommendController.recommend(user);
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
//                    public void actionPerformed(ActionEvent e)
//                    {
//                        int currRow = Integer.valueOf( e.getActionCommand() );
//
//                        //get target name from the 8th column of the table
//                        String targetName = (String) table.getModel().getValueAt(currRow, 8);
//                        likeController.like(userName,targetName);
//                        String message = likePresenter.prepareSuccessView(targetName);
//                        JOptionPane.showMessageDialog(getContentPane(),message);
//                    }
//                };
//                ButtonColumnLike likeButton = new ButtonColumnLike(table, likeAction, 9);

//                // Create the Button Review
//                JButton ReviewButton = new JButton("Review");
//                LikeButton.addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent arg) {
//                        String targetName = currentUser.getAccountName();
////                        likeController.like(userName,targetName);
//                        JOptionPane.showMessageDialog(getContentPane(),"You Wrote Reviewed" + targetName);
//                    }
//                });
//                model.setValueAt(ReviewButton, i, 10);
//
//
//                // Create the Button Review
//                JButton ReportButton = new JButton("Report");
//                LikeButton.addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent arg) {
//                        String targetName = currentUser.getAccountName();
////                        likeController.like(userName,targetName);
//                        JOptionPane.showMessageDialog(getContentPane(),"You successfully reported" + targetName);
//                    }
//                });
//
//                model.setValueAt(ReportButton, i, 11);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
