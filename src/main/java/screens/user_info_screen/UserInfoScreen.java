package screens.user_info_screen;


import Gateway.DatabaseConnect;

import entities.Profile;

import screens.regularuser_register_screen.LabelTextPanel;
import screens.review_screen.IReviewViewImplementation;
import screens.review_screen.ReviewCreationScreen;
import use_cases.*;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.review_use_case.ReviewController;
import use_cases.review_use_case.ReviewGatewayImplementation;
import use_cases.review_use_case.ReviewInteractor;
import use_cases.review_use_case.ReviewPresenter;
import use_cases.user_manager_user_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class UserInfoScreen extends JPanel implements UserInformation, ActionListener {

    private UserController controller;
    private JTextField username = new JTextField(20);

    private JTextField revId = new JTextField(20);

    public static void main(String[] args) throws IOException {
        UserInfoScreen frame = new UserInfoScreen();
        frame.setVisible(true);
    }

    public UserInfoScreen(){
        JFrame application = new JFrame("User Information");
        application.setLayout(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        UserOutputBoundary presenter = new UserPresenter(this);
        DatabaseConnect db = new DatabaseConnect(System.getProperty("user.dir"));
        UserInputBoundary interactor = new UserManagerInteractor(db, presenter);
        this.controller = new UserController(interactor);



        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Type username to confirm:"), username);

        application.add(usernameInfo);

        JButton button1 = new JButton("Confirm");
        application.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSuccessMessage("Confirmed Successfully!");
            }
        });

        JButton button = new JButton("Profile");
        JButton buttonLiked = new JButton("Liked");
        JButton buttonStatus = new JButton("Status");
        JButton buttonReview = new JButton("Show Review");
        JButton buttonUpgrade = new JButton("Upgrade");
//        JButton buttonHideReview = new JButton("Hide Review (VIP)");
        JButton buttonLikeMe = new JButton("Liked Me (VIP)");
        JButton buttonInvisible = new JButton("Set as Invisible (VIP)");

        button.addActionListener(this);
        button.setPreferredSize(new Dimension(50, 50));
        buttonLiked.addActionListener(this);
        buttonLiked.setPreferredSize(new Dimension(50, 50));
        buttonStatus.addActionListener(this);
        buttonStatus.setPreferredSize(new Dimension(50, 50));
        buttonReview.addActionListener(this);
        buttonReview.setPreferredSize(new Dimension(100, 50));
        buttonUpgrade.addActionListener(this);
        buttonUpgrade.setPreferredSize(new Dimension(90, 50));
//        buttonHideReview.addActionListener(this);
//        buttonHideReview.setPreferredSize(new Dimension(150, 50));
        buttonLikeMe.addActionListener(this);
        buttonLikeMe.setPreferredSize(new Dimension(100, 50));
        buttonInvisible.addActionListener(this);
        buttonInvisible.setPreferredSize(new Dimension(150, 50));

        application.repaint();
        application.add(button);
        application.add(buttonLiked);
        application.add(buttonStatus);
        application.add(buttonReview);
        application.add(buttonUpgrade);
//        application.add(buttonHideReview);
        application.add(buttonLikeMe);
        application.add(buttonInvisible);
        application.pack();
        application.setVisible(true);

    }

    @Override
    public void showLiked(List<String> accName) {
        JFrame application = new JFrame("User I Liked");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        for (String s : accName){
            JLabel acc = new JLabel(s);
            application.add(acc);
        }

        application.setLayout(new GridLayout(accName.toArray().length, 7, 0, 0));


        application.pack();
        application.setVisible(true);
    }

    public void showFailedMessage(String message){
        JFrame application = new JFrame("Failed!!");
        application.getContentPane().setBackground( Color.RED );
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        JLabel fail = new JLabel(message);
        application.add(fail);
        application.setVisible(true);
    }

    @Override
    public void showAccStatus(boolean status, float time) {
        JFrame application = new JFrame("Account Status");
        application.setLayout(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        JLabel vip = new JLabel("VIP status is: " + String.valueOf(status).toUpperCase());
        application.add(vip);
        JLabel restriction;
        JLabel remainTime;
        if (time != 0) {
            restriction = new JLabel("Account Restricted: TRUE");
        }else{
            restriction = new JLabel("Account Restricted: FALSE");
        }
        remainTime = new JLabel(String.valueOf(time));
        application.add(restriction);
        application.add(remainTime);
        application.setVisible(true);
    }

//    public boolean showConfirmWindow(){
//        JFrame application = new JFrame("ARE U SURE?");
//        application.getContentPane().setBackground( Color.gray );
//        application.setLayout(new FlowLayout());
//        CardLayout cardLayout = new CardLayout();
//        JPanel screens = new JPanel(cardLayout);
//        application.add(screens);
//        JTextField response = new JTextField(3);
//        LabelTextPanel confirm = new LabelTextPanel(
//                new JLabel("Type 'yes' to confirm, 'no' to cancel"), response);
//        application.add(confirm);
//        JButton confirmButton = new JButton("I Confirm");
//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == confirmButton) {
//                    showSuccessMessage("Confirmed!");
//
//                }
//
//            }
//        });
//        application.add(confirmButton);
//        application.setVisible(true);
//        System.out.println(response.getText());
//        return Objects.equals(response.getText(), "yes");
//    }



//    public String showHideReviewWindow(){
//        JFrame application = new JFrame("Hide Review");
//        application.getContentPane().setBackground( Color.gray );
//        application.setLayout(new FlowLayout());
//        CardLayout cardLayout = new CardLayout();
//        JPanel screens = new JPanel(cardLayout);
//        application.add(screens);
//        JTextField response = new JTextField(15);
//        LabelTextPanel hide = new LabelTextPanel(
//                new JLabel("Type the id of review you want to hide"), revId);
//        application.add(hide);
//        JButton confirmButton = new JButton("I Confirm");
//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showSuccessMessage("Hiding Review");
//            }
//        });
//        application.add(confirmButton);
//        application.pack();
//        application.setVisible(true);
//        return revId.getText();
//    }

    @Override
    public void showSuccessMessage(String message) {
        JFrame application = new JFrame("Success");
        application.getContentPane().setBackground( Color.green );
        application.setLayout(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        JLabel success = new JLabel(message);
        application.add(success);
        application.setVisible(true);
    }

    @Override
    public void showProfile(Profile profile) {
        JFrame application = new JFrame("User Profile");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        JLabel ageValue = new JLabel("Age:" + profile.getAge());

        JLabel genderValue = new JLabel("Gender:" + profile.getGender());

        JLabel locationValue = new JLabel("Location:" + profile.getLocation());

        JLabel sexualityValue = new JLabel("Sexuality:" + profile.getSexuality());

        JLabel hobbyValue = new JLabel("Hobbies:" + profile.getHobbies());

        JLabel heightValue = new JLabel("Height:" + profile.getHeight());

        JLabel weightValue = new JLabel("Weight:" + profile.getWeight());

        JLabel contactInfoValue = new JLabel("Contact Information:" + profile.getContactInformation());

        JLabel selfDescriptionValue = new JLabel("Self Description:" + profile.getSelfDescription());

        application.setLayout(new GridLayout(9, 0, 0, 0));

        application.add(ageValue);
        application.add(genderValue);
        application.add(locationValue);
        application.add(sexualityValue);
        application.add(hobbyValue);
        application.add(heightValue);
        application.add(weightValue);
        application.add(contactInfoValue);
        application.add(selfDescriptionValue);
        application.pack();
        application.setVisible(true);
    }

    @Override
    public void showLikedMe(List<String> likedme) {
        JFrame application = new JFrame("Liked Me");
        application.setLayout(new FlowLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        for (String s : likedme){
            JLabel acc = new JLabel(s);
            application.add(acc);
        }
        application.setLayout(new GridLayout(likedme.toArray().length, 7, 0, 0));
        application.pack();
        application.setVisible(true);

    }

    public void showReview(Hashtable<Integer, List<Object>> reviews){
        JFrame application = new JFrame("Reviews");
        application.setLayout(new GridLayout(50, 1));
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
        for (Map.Entry<Integer, List<Object>> entry : reviews.entrySet()) {
            JLabel acc = new JLabel("id:" + entry.getKey() + " - " + entry.getValue().get(0) + ", " + entry.getValue().get(1));
            application.add(acc);
        }
        application.pack();
        application.setVisible(true);

    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        UserRequestModel model = new UserRequestModel(
                username.getText(),
                0);

        if (Objects.equals(evt.getActionCommand(), "Profile")) {
            try {
                controller.showProfile(model);
                JOptionPane.showMessageDialog(this, username.getText() + "'s profile is displayed.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (Objects.equals(evt.getActionCommand(), "Liked")) {
            try {
                controller.viewLiked(model);
                JOptionPane.showMessageDialog(this, "Displaying Users I Liked.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (Objects.equals(evt.getActionCommand(), "Status")){
            try {
                controller.viewAccountStatus(model);
                JOptionPane.showMessageDialog(this, "Displaying Account Status.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else if (Objects.equals(evt.getActionCommand(), "Show Review")) {
            try {
                controller.displayReview(model);
                JOptionPane.showMessageDialog(this, "Displaying Reviews.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else if (Objects.equals(evt.getActionCommand(), "Upgrade")) {
            try {
                UserOutputBoundary presenter3 = new UserPresenter(this);
                DatabaseConnect db3 = new DatabaseConnect(System.getProperty("user.dir"));
                RegularUserManager RegUserManager = new RegularUserManager(db3, presenter3);
                UserController controller3 = new UserController(RegUserManager);
                controller3.changeVIPStatus(model);
                JOptionPane.showMessageDialog(this, "Upgrade to VIP");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
//        else if (Objects.equals(evt.getActionCommand(), "Hide Review (VIP)")) {
//            try {
//                UserOutputBoundary presenter4 = new UserPresenter(this);
//                DatabaseConnect db4 = new DatabaseConnect(System.getProperty("user.dir"));
//                VIPUserManager vipUserManager = new VIPUserManager(db4, presenter4);
//                UserController controller4 = new UserController(vipUserManager);
//                String revIds = showHideReviewWindow();
//                controller4.hideReviewVIP(model, Integer.valueOf(revIds));
//                JOptionPane.showMessageDialog(this, "Upgrade to VIP");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e.getMessage());
//            }
//        }
        else if(Objects.equals(evt.getActionCommand(), "Liked Me (VIP)")){
            try {
                UserOutputBoundary presenter1 = new UserPresenter(this);
                DatabaseConnect db1 = new DatabaseConnect(System.getProperty("user.dir"));
                VIPUserManager userManagerInteractor = new VIPUserManager(db1, presenter1);
                UserController controller1 = new UserController(userManagerInteractor);
                controller1.viewLikedMeVIP(model);
                JOptionPane.showMessageDialog(this, "Displaying Users Liked Me.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }else if(Objects.equals(evt.getActionCommand(), "Set as Invisible (VIP)")){
            try {
                UserOutputBoundary presenter1 = new UserPresenter(this);
                DatabaseConnect db1 = new DatabaseConnect(System.getProperty("user.dir"));
                VIPUserManager userManagerInteractor = new VIPUserManager(db1, presenter1);
                UserController controller1 = new UserController(userManagerInteractor);
                controller1.setInvisibleVisit(model, true);
                JOptionPane.showMessageDialog(this, "You are in Invisible Mode");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

}
