package use_cases.regular_user_register_use_case;

import entities.Profile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;
import use_cases.recommend_use_case.RecommendGateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileGatewayTest {

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
    void createProfile() throws IOException, InterruptedException {

        initializeDataset();

        FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/profiles.xls"));
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(1);

        ProfileGateway profileGateway = new ProfileGateway(System.getProperty("user.dir"));
        Profile profileCreated = profileGateway.CreateProfile(row);
        assertNotNull(profileCreated);
        assertEquals("atherton, california", profileCreated.getLocation());

    }

    @Test
    void findProfile() throws IOException, InterruptedException {

        initializeDataset();
        ProfileGateway profileGateway = new ProfileGateway(System.getProperty("user.dir"));
        Profile profileFound = profileGateway.findProfile("acc0");
        assertNotNull(profileFound);
        assertEquals(profileFound.getLocation(), "south san francisco, california");

    }
}