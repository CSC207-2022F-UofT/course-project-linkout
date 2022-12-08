package use_cases.user_manager_user_case;


import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface UserInputBoundary {

    void viewLiked(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

    void viewAccountStatus(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

    void showReview(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;


    void setRestrictionTime(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

    void showProfile(UserRequestModel userRequestModel) throws IOException, InvalidAttributeValueException;

}
