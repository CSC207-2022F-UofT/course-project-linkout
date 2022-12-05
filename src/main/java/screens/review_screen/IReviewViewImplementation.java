package screens.review_screen;

import javax.swing.*;

public class IReviewViewImplementation implements IReviewView{

    @Override
    public void updateMessage(){
        JOptionPane.showMessageDialog(null, "Review has successfully created.");
    }
}
