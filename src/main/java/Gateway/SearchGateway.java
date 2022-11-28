
package Gateway;
import use_cases.search_use_case.SearchDSGateway;

import entities.Profile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

public class SearchGateway extends DatabaseGateway implements SearchDSGateway {

    public SearchGateway(String workingdir) {
        super(workingdir);
    }

    public HSSFSheet getSheetOfAllUsers(){
        // Return a sheet containing all registered users for seaech

        HSSFWorkbook wb = ProfileStyleBook;
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        return sheet;
    }


    public void SaveSeen(String username, List<String> usersviewed) throws IOException {
        recommendGateway.SaveSeen(username, usersviewed);
    }
}

