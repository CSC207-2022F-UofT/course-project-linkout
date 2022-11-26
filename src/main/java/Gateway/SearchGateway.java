package Gateway;

import entities.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchGateway extends DatabaseGateway {

    private UserGateway userGateway;
    private RecommendGateway recommendGateway;

    public SearchGateway(String workingdir) {
        super(workingdir);
        userGateway = new UserGateway(workingdir);
        recommendGateway = new RecommendGateway(workingdir);
    }

    public List<User> searchSheet(String searchTexts) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);

        // Create a variable to store rows that contains the searchText
        List<User> filteredUsers = new ArrayList<>();

        // Convert searchTexts to all lowercase to match database
        String searchText = searchTexts.toLowerCase();

        //Iterate rows
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

            Row row = sheet.getRow(i);
            String currtext;
            User user;

            // Iterate columns
            for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                Cell cell = row.getCell(k);

                // Handle empty cells
                if (cell == null) {
                    continue;
                }

                currtext = loadStringCell(cell).toLowerCase();
                if (searchText.equals(currtext)) {
                    user = userGateway.findUser(loadStringCell(row.getCell(8)));
                    filteredUsers.add(user);
                    break;
                }

            }
        }
        // return an arraylist that contains the matched users along with their corresponding profiles
        return filteredUsers;
    }

    public void SaveSeen(String username, String userviewed) throws IOException {
        recommendGateway.SaveSeen(username, userviewed);
    }

}
