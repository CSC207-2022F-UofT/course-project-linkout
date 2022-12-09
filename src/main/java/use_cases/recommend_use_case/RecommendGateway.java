package use_cases.recommend_use_case;

import Gateway.DatabaseGateway;
import use_cases.user_action_use_case.LikesGateway;
import use_cases.regular_user_register_use_case.ProfileGateway;
import use_cases.review_use_case.ReviewGatewayImplementation;
import entities.Profile;
import entities.RegularUser;
import entities.User;
import entities.VipUser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RecommendGateway extends DatabaseGateway implements RecommendDsGateway {

    private final ProfileGateway profileGateway;
    private final LikesGateway likesGateway;

    private final ReviewGatewayImplementation reviewGateway;

    /**
     * @param workingdir the current working directory
     */
    public RecommendGateway(String workingdir) {
        super(workingdir);
        profileGateway = new ProfileGateway(workingdir);
        likesGateway = new LikesGateway(workingdir);
        reviewGateway = new ReviewGatewayImplementation(workingdir);
    }

    /**
     * @return the working directory
     */
    @Override
    public String getWorkingDir(){
        return super.workingdir;
    }

    /**
     * @param type the type of file you would like to load
     * @return a list of users
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    @Override
    public List<User> loadAllUser(String type) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfilesStyleBook(type);
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        List<User> users = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Profile profile = profileGateway.CreateProfile(row);
            String usrname = row.getCell(8).toString();
            String password = row.getCell(9).toString();
            String isVip = row.getCell(10).toString();
            List<String> liked = likesGateway.findLiked(usrname);
            List<String> likedme = likesGateway.findLikedMe(usrname);
            Hashtable<Integer, List<Object>> reviews = reviewGateway.getReviews(usrname);
            User user;
            if (isVip.equals("TRUE")){
                user = new VipUser(password, usrname, profile, true, liked, likedme, reviews);
            } else {
                user = new RegularUser(password, usrname, profile, liked, likedme, reviews);
            }
            users.add(user);
        }
        return users;
    }

    /**
     * Check whether a user has liked other users
     * @param username the username of the user
     * @return a boolean of whether one has liked other users
     * @throws IOException Can't find database
     */
    @Override
    public boolean hasLiked(String username) throws IOException {
        List<String> liked = likesGateway.findLiked(username);
        return (!liked.isEmpty());
    }

    /**
     * check whether a user has seen another user
     * @param username the username of the user
     * @param usersviewed the username of the target user
     * @throws IOException Can't find database
     * @throws InvalidAttributeValueException Wrong type
     */
    @Override
    public void saveSeen(String username, List<User> usersviewed) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);

        for (User userviewed:usersviewed){
            if (likesGateway.isSeen(username, userviewed.getAccountName())){
                return;
            }
            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
            row.createCell(0).setCellValue(username);
            row.createCell(1).setCellValue(userviewed.getAccountName());
            row.createCell(2).setCellValue(0);
        }
        SaveWorkbook(wb,"likes");
    }

}
