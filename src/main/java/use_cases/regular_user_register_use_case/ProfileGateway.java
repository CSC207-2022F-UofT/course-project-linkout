package use_cases.regular_user_register_use_case;

import Gateway.DatabaseGateway;
import entities.Profile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

public class ProfileGateway extends DatabaseGateway {

    public ProfileGateway(String workingdir) {
        super(workingdir);
    }

    public Profile CreateProfile(Row row) {
        String location = loadStringCell(row.getCell(2));
        String gender = loadStringCell(row.getCell(1));
        String age = loadStringCell(row.getCell(0));
        String sexuality = loadStringCell(row.getCell(3));
        String hobbies = loadStringCell(row.getCell(7));
        String height = loadStringCell(row.getCell(4));
        String weight = loadStringCell(row.getCell(6));
        String contactInformation = loadStringCell(row.getCell(11));
        String selfDescription = loadStringCell(row.getCell(5));
        Profile profile = new Profile(
                location, gender, age, sexuality, hobbies, height, weight, contactInformation, selfDescription
        );
        return profile;
    }

    public Profile findProfile(String usrname) throws IOException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        Profile profile = null;
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                profile = CreateProfile(row);
                break;
            }
        }

        return profile;
    }



}
