package use_cases.regular_user_register_use_case;

import entities.Profile;
import entities.RegularUser;
import entities.User;
import entities.VipUser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import use_cases.recommend_use_case.RecommendGateway;

import javax.management.InvalidAttributeValueException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserGatewayTest {

    public void initializeDataset() throws IOException, InterruptedException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Reinitiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/profiles.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/profiles.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2).waitFor();
    }

    @Test
    void findUser() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        User userFound = userGateway.findUser("acc0");
        assertNotNull(userFound);
        assertEquals(userFound.getAccountName(), "acc0");
        assertEquals(userFound.displayProfile().getLocation(), "south san francisco, california");

        User userFound2 = userGateway.findUser("acc1");
        assertNotNull(userFound2);
        assertEquals(userFound2.getAccountName(), "acc1");
        assertTrue(userFound2 instanceof VipUser);

    }

    @Test
    void existsByName() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();
        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        assertTrue(userGateway.existsByName("acc0"));
        assertFalse(userGateway.existsByName("abcde"));

    }

    @Test
    void saveUser() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();

        UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
        FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/profiles.xls"));
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        int numRowsBefore = sheet.getPhysicalNumberOfRows();

        UserRegisterDsRequestModel toSave = new UserRegisterDsRequestModel("accTest", "test", "test",
                "m", "20", "straight", "", "", "", "", "", LocalDateTime.now());
        userGateway.saveUser(toSave);

        fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/profiles.xls"));
        wb=new HSSFWorkbook(fis);
        sheet = wb.getSheetAt(0);
        int numRowsAfter = sheet.getPhysicalNumberOfRows();

        assertEquals(numRowsAfter-1, numRowsBefore);
        assertEquals(sheet.getRow(sheet.getLastRowNum()).getCell(8).toString(), "accTest");
        assertEquals(sheet.getRow(sheet.getLastRowNum()).getCell(9).toString(), "test");

        initializeDataset();

    }


}