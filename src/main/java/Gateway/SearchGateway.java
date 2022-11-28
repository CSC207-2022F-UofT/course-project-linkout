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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        HSSFSheet sheet = wb.getSheetAt(0);


        // Create a variable to store rows that contains the searchText
        List<User> filteredUsers = new ArrayList<>();

        // Convert searchTexts to all lowercase to match database
        String searchText = searchTexts.toLowerCase();

        // Separate the search text into individual keyword(e.g.['tennis', 'gay'])
        String[] searchTextList = searchText.split(",");

        // Iterate through the keywords
        for (int i = 0; i < searchTextList.length; i++) {

            //Iterate rows
            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {

                Row row = sheet.getRow(j);

                // Handle when searchText is double
                Double doubleValue = null;
                try {
                    doubleValue = Double.parseDouble(searchTextList[i]);
                } catch (Exception e) {
                }

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
        }
        // Only keep rows that has been duplicated for n(number of keywords) times(i.e. keep rows that matched with
        // all keywords
        // dup is the filter list that contains only users satisfies all keywords entered
        ArrayList<User> dup = new ArrayList<>();
        int numberOfKeyword = searchTextList.length;

        Map<User, Long> occurrences = filteredUsers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        occurrences.values().removeIf(v -> v < numberOfKeyword);
        for (User user : occurrences.keySet()) {
            dup.add(user);
        }
        // Create a sub-arraylist that contains 20 of the matched users along with their corresponding profiles
        ArrayList<User> twentyMatchedUsers = new ArrayList<>();
        for (int idx = 0; idx < 21; idx++) {
            twentyMatchedUsers.add(dup.get(idx));
        }

        // return the 20 matched users along with their corresponding profiles
        return twentyMatchedUsers;
    }




    public void SaveSeen (String username, List<String> usersviewed) throws IOException {
        recommendGateway.SaveSeen(username, usersviewed);
    }
}
