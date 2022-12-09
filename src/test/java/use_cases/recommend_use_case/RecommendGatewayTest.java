package use_cases.recommend_use_case;

import entities.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import use_cases.regular_user_register_use_case.UserGateway;

class RecommendGatewayTest {

    public void initializeDataset() throws IOException, InterruptedException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Reinitiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/likes.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/likes.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2).waitFor();
    }

    @Test
    void getWorkingDir() {
        String workingdir = System.getProperty("user.dir");
        RecommendGateway db = new RecommendGateway(workingdir);
        assertEquals(workingdir, db.getWorkingDir());
    }

    @Test
    void loadAllUser() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        RecommendRequestModel requestModel = new RecommendRequestModel("acc1");
        RecommendInteractor interactor = new RecommendInteractor(db){
            @Override
            public RecommendResponseModel Recommend(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
                List<User> recommend = db.loadAllUser("recommend");
                assertNotNull(recommend);

                FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/data/recommend.xls");
                HSSFWorkbook wb=new HSSFWorkbook(fis);
                int numUsers = wb.getSheetAt(0).getPhysicalNumberOfRows() - 1;
                assertEquals(recommend.size(), numUsers);

                return null;
            }
        };
        interactor.Recommend(requestModel);
    }

    @Test
    void hasLiked() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        assertTrue(db.hasLiked("acc1"));
        assertFalse(db.hasLiked("acc0"));
    }

    @Test
    void saveSeen() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        RecommendRequestModel requestModel = new RecommendRequestModel("acc1");
        RecommendInteractor interactor = new RecommendInteractor(db){
            @Override
            public RecommendResponseModel Recommend(RecommendRequestModel requestModel) throws IOException, InvalidAttributeValueException {
                FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/data/likes.xls");
                HSSFWorkbook wb=new HSSFWorkbook(fis);
                HSSFSheet sheet = wb.getSheetAt(0);
                int numRowsBefore = sheet.getPhysicalNumberOfRows();

                List<User> toSave = new ArrayList<>();
                toSave.add(userGateway.findUser("acc1"));
                db.saveSeen("acc0", toSave);

                fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/data/likes.xls");
                wb=new HSSFWorkbook(fis);
                sheet = wb.getSheetAt(0);
                int numRowsAfter = sheet.getPhysicalNumberOfRows();

                assertEquals(numRowsAfter-1, numRowsBefore);
                assertEquals(sheet.getRow(sheet.getLastRowNum()).getCell(0).toString(), "acc0");
                assertEquals(sheet.getRow(sheet.getLastRowNum()).getCell(1).toString(), "acc1");

                return null;
            }
        };
        interactor.Recommend(requestModel);
        initializeDataset();

    }
}