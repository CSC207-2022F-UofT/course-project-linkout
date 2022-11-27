package Gateway;

import RecommendUseCase.RecommendDsGateway;
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

    private ProfileGateway profileGateway;
    private LikesGateway likesGateway;

    private ReviewGateway reviewGateway;

    public RecommendGateway(String workingdir) {
        super(workingdir);
        profileGateway = new ProfileGateway(workingdir);
        likesGateway = new LikesGateway(workingdir);
        reviewGateway = new ReviewGateway(workingdir);
    }


    @Override
    public List<User> LoadAllUser(String type) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfileStyleBook(type);
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

    public void SaveSeen(String username, String userviewed) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        if (likesGateway.isSeen(username, userviewed)){
            return;
        }
        Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
        row.createCell(0).setCellValue(username);
        row.createCell(1).setCellValue(userviewed);
        row.createCell(2).setCellValue(0);
    }

}
