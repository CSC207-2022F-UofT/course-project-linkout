package use_cases.regular_user_register_use_case;

import Gateway.DatabaseGateway;
import use_cases.user_manager_user_case.UserUpgrade;
import entities.*;
import use_cases.record_report_use_case.RecordReportGateway;
import use_cases.restrict_user_use_case.RestrictUserGateway;
import use_cases.user_action_use_case.LikesGateway;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import use_cases.review_use_case.ReviewGatewayImplementation;
import use_cases.user_login_use_case.UserLoginDsGateway;
import use_cases.user_manager_user_case.UserDsGateway;


import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class UserGateway extends DatabaseGateway implements UserRegisterDsGateway, RecordReportGateway, UserUpgrade,
        UserLoginDsGateway, UserDsGateway, RestrictUserGateway{

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
        return likesGateway.findUser(usrname);
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
        if (existsByName(requestModel.getName())) {
            return;
        }
        HSSFWorkbook wb = ProfilesStyleBook();
        HSSFSheet sheet = wb.getSheetAt(0);

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




    public boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(accName)) {
                sheet.getRow(i).getCell(10).setCellValue(status);
                SaveWorkbook(wb, "profiles");
                return true;
            }
        }

        return false;

    }

    @Override
    public boolean MatchingNameAndPassword(String accountName, String Password) throws IOException, InvalidAttributeValueException {
        if (!existsByName(accountName)) {
            return false;
        }

        User userLogin = findUser(accountName);
        String password = userLogin.getPassword();

        return password.equals(Password);
    }

    @Override
    public boolean NotExist(String accountName) throws IOException, InvalidAttributeValueException {
        return !existsByName(accountName);
    }
}

