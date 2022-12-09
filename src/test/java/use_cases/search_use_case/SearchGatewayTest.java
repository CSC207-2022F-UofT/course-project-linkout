package use_cases.search_use_case;

import entities.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import use_cases.regular_user_register_use_case.UserGateway;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchGatewayTest {

    @Test
    public void SearchGatewayTests() throws IOException, InvalidAttributeValueException {

        SearchGateway searchGateway = new SearchGateway(System.getProperty("user.dir"));

        // Test searchSheet
        //Input data
        String keywords = "22,m";
        String userName = "acc1";
        List<User> matchedUser = searchGateway.searchSheet(keywords, userName);
        SearchResponseModel responseModel1 = new SearchResponseModel(matchedUser);
        Assertions.assertNotNull(responseModel1.getTwentyMatchedUsers());
        Assertions.assertTrue(responseModel1.getTwentyMatchedUsers() instanceof List);
        Assertions.assertNotNull(responseModel1.getTwentyMatchedUsers());
    }

    @Test
    void getWorkingDir() {
        String workingdir = System.getProperty("user.dir");
        SearchGateway db = new SearchGateway(workingdir);
        assertEquals(workingdir, db.getWorkingDir());
    }

    @Test
    void saveSeen() throws IOException, InvalidAttributeValueException{

        SearchGateway db = new SearchGateway(System.getProperty("user.dir"));
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        SearchRequestModel requestModel = new SearchRequestModel("acc1", "acc0");
        SearchInteractor interactor = new SearchInteractor(db){
            @Override
            public SearchResponseModel searchSheet(SearchRequestModel requestModel) throws IOException, InvalidAttributeValueException {
                FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/likes.xls"));
                HSSFWorkbook wb=new HSSFWorkbook(fis);
                HSSFSheet sheet = wb.getSheetAt(0);

                List<User> toSave = new ArrayList<>();
                toSave.add(userGateway.findUser("acc0"));
                db.SaveSeen("acc1", toSave);

                fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/likes.xls"));
                wb=new HSSFWorkbook(fis);
                sheet = wb.getSheetAt(0);

                assertEquals(sheet.getRow(sheet.getLastRowNum()).getCell(0).toString(), "acc1");
                assertEquals(sheet.getRow(sheet.getLastRowNum()).getCell(1).toString(), "acc0");

                return null;
            }
        };
        interactor.searchSheet(requestModel);
    }
}
