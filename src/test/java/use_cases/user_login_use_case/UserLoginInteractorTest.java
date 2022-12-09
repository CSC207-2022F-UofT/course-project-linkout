package use_cases.user_login_use_case;

import entities.User;
import entities.VipUser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import use_cases.recommend_use_case.RecommendGateway;
import use_cases.regular_user_register_use_case.UserGateway;
import use_cases.regular_user_register_use_case.UserRegisterDsRequestModel;
import use_cases.regular_user_register_use_case.UserRegisterRequestModel;

import javax.management.InvalidAttributeValueException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginInteractorTest {
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
    UserLoginRequestModel requestModel = new UserLoginRequestModel("zzz","123123");
        @Test
        void create() throws IOException, InvalidAttributeValueException, InterruptedException {

            initializeDataset();
            UserGateway userGateway = new UserGateway(System.getProperty("user.dir"));
            User userFound = userGateway.findUser("acc0");
            assertNotNull(userFound);
            assertEquals(requestModel.getUsername(), "zzz");
            assertEquals(requestModel.getPassword(), "123123");


        }
}