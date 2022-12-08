package use_cases.regular_user_register_use_case;

import Gateway.DatabaseGateway;
import entities.*;
import use_cases.record_report_use_case.RecordReportGateway;
import use_cases.user_action_use_case.LikesGateway;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import use_cases.review_use_case.ReviewGatewayImplementation;


import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class UserGateway extends DatabaseGateway implements UserRegisterDsGateway, RecordReportGateway {

    private ProfileGateway profileGateway;

    private LikesGateway likesGateway;

    private ReviewGatewayImplementation reviewGateway;
    public UserGateway(String workingdir) {
        super(workingdir);
        profileGateway = new ProfileGateway(workingdir);
        likesGateway = new LikesGateway(workingdir);
        reviewGateway = new ReviewGatewayImplementation(workingdir);
    }

    @Override
    public User findUser(String usrname) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        User user = null;
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                Profile profile = profileGateway.CreateProfile(row);
                String password = loadStringCell(row.getCell(9));
                String isVip = loadStringCell(row.getCell(10));
                List<String> liked = likesGateway.findLiked(usrname);
                List<String> likedme = likesGateway.findLikedMe(usrname);
                Hashtable<Integer, List<Object>> reviews = reviewGateway.getReviews(usrname);
                if (isVip.equals("TRUE")){
                    user = new VipUser(password, usrname, profile, true, liked, likedme, reviews);
                } else {
                    user = new RegularUser(password, usrname, profile, liked, likedme, reviews);
                }
                break;
            }
        }
        return user;
    }

    @Override
    public Admin findAdmin(String adminID) {
        return new Admin("Admin", adminID);
    }

    @Override
    public boolean existsByName(String username) throws IOException, InvalidAttributeValueException {
        return findUser(username) != null;
    }

    @Override
    public void saveUser(UserRegisterDsRequestModel requestModel) throws InvalidAttributeValueException, IOException {
        if (existsByName(requestModel.getName())){
            return;
        }
        HSSFWorkbook wb = ProfilesStyleBook();
        HSSFSheet sheet=wb.getSheetAt(0);

        Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
        row.createCell(0).setCellValue(requestModel.getAge());
        row.createCell(1).setCellValue(requestModel.getGender());
        row.createCell(2).setCellValue(requestModel.getLocation());
        row.createCell(3).setCellValue(requestModel.getSexuality());
        row.createCell(4).setCellValue(requestModel.getHeight());
        row.createCell(5).setCellValue(requestModel.getSelfDescription());
        row.createCell(6).setCellValue(requestModel.getWeight());
        row.createCell(7).setCellValue(requestModel.getHobbies());
        row.createCell(8).setCellValue(requestModel.getName());
        row.createCell(9).setCellValue(requestModel.getPassword());
        row.createCell(10).setCellValue("FALSE");
        row.createCell(11).setCellValue(requestModel.getContactInformation());
        row.createCell(12).setCellValue(requestModel.getRestrictionStartTime());
        row.createCell(13).setCellValue(requestModel.getRestrictionDuration());

        SaveWorkbook(wb, "profiles");
    }


    public void RemoveUser(User user) throws IOException, InvalidAttributeValueException {
        String username = user.getAccountName();
        if (existsByName(username)){
            return;
        }
        HSSFWorkbook wb = ProfilesStyleBook();
        HSSFSheet sheet=wb.getSheetAt(0);

        String currusername;

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currusername = loadStringCell(sheet.getRow(i).getCell(8));
            if (currusername == username) {
                sheet.removeRow(sheet.getRow(i));
            }
        }

        SaveWorkbook(wb, "profiles");
    }
}