package screens.review_screen;

import javax.swing.*;

public class ReviewCreationSuccessScreen implements IReviewView{
    @Override
    public void updateMessage() {
        JOptionPane.showMessageDialog(null,"Review created");
    }
}
