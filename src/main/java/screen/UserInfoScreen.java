package screen;

import entities.Profile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserInfoScreen implements UserInformation{


    public UserInfoScreen(){
        JFrame application = new JFrame("User Information");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);
    }

    @Override
    public void showLiked(List<String> accName) {
        //Jpanel...
    }

    public void showFailedMessage(String message){

    }

    @Override
    public void showAccStatus(boolean status, float time) {

    }

    @Override
    public void showVipStatus(boolean status) {

    }

    @Override
    public void showRestrictionStatus(float time) {

    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showProfile(Profile profile) {

    }

    @Override
    public void showLikedMe(List<String> likedme) {

    }

    @Override
    public void showVisitor(List<String> visitor) {

    }


}
