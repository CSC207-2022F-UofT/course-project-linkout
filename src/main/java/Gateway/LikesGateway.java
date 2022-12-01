package Gateway;

import entities.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.user_action_use_case.UserActDsGateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LikesGateway extends DatabaseGateway implements UserActDsGateway {
    private UserGateway userGateway;


    public LikesGateway(String workingdir) {
        super(workingdir);
    }

    public LikesGateway(String workingdir, UserGateway userGateway) {
        super(workingdir);
        this.userGateway = userGateway;
    }


    public List<String> findLiked(String usrname) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        List<String> liked = new ArrayList<String>();
        String currname;
        String likeuser;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(0).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                likeuser = loadStringCell(row.getCell(1));
                liked.add(likeuser);
            }
        }

        return liked;
    }

    public List<String> findLikedMe(String usrname) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        List<String> liked = new ArrayList<String>();
        String currname;
        String likeuser;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = loadStringCell(sheet.getRow(i).getCell(1));
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                likeuser = loadStringCell(row.getCell(0));
                liked.add(likeuser);
            }
        }

        return liked;
    }

    public boolean isSeen(String username, String userviewed) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        String currname;
        String curruserviewed;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = loadStringCell(sheet.getRow(i).getCell(0));
            curruserviewed = loadStringCell(sheet.getRow(i).getCell(1));
            if ((currname.equals(username)) & (curruserviewed.equals(userviewed))) {
                return true;
            }
        }

        return false;
    }

    public boolean isLiked(String username, String userviewed) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        String currname;
        String curruserviewed;
        int liked;
        if (!isSeen(username, userviewed)){
            return false;
        }
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = loadStringCell(sheet.getRow(i).getCell(0));
            curruserviewed = loadStringCell(sheet.getRow(i).getCell(1));
            liked = loadIntCell(sheet.getRow(i).getCell(2));
            if ((currname.equals(username)) & (curruserviewed.equals(userviewed)) & (liked == 1)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public User findUser(String accName) {
        try {
            return userGateway.findUser(accName);
        } catch (IOException | InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLike(String username, String userviewed) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        String currname;
        String curruserviewed;
        int liked;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = loadStringCell(sheet.getRow(i).getCell(0));
            curruserviewed = loadStringCell(sheet.getRow(i).getCell(1));
            liked = loadIntCell(sheet.getRow(i).getCell(2));
            if ((currname.equals(username)) & (curruserviewed.equals(userviewed)) & (liked == 0)) {
                sheet.getRow(i).getCell(2).setCellValue(1);
            }
        }

    }

}
