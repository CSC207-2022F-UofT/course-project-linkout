package use_cases.user_use_case;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserInputBoundary {

    public void viewLiked(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

    public void viewAccountStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

    public void showReview(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;




    public void setRestrictionTime(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

    public void showProfile(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

}
