package use_cases.search_use_case;
import Gateway.DatabaseGateway;

import use_cases.regular_user_register_use_case.ProfileGateway;
import use_cases.review_use_case.ReviewGatewayImplementation;
import entities.RegularUser;
import entities.User;
import entities.VipUser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import entities.Profile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import use_cases.user_action_use_case.LikesGateway;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchGateway extends DatabaseGateway implements SearchDSGateway{
    private LikesGateway likesGateway;
    private ReviewGatewayImplementation reviewGateway;

    private ProfileGateway profileGateway;
    public SearchGateway(String workingdir)  {
        super(workingdir);
        likesGateway = new LikesGateway(workingdir);
        reviewGateway = new ReviewGatewayImplementation(workingdir);
        profileGateway = new ProfileGateway(workingdir);
    }

    /**
     * this method <searchSheet> searches for the first users that matched the keywords entered from the database and return a
     * SearchResponseModel object which includes an arraylist of 20 matched users along with their corresponding profiles.
     *
     * @param keywords the keywords entered by the users
     * @return List<User> a list of 20 matched users along with their corresponding profiles
     */

    public List<User> searchSheet(String keywords, String username) throws IOException, InvalidAttributeValueException {

        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);

        String searchTexts = keywords;

        // Convert searchTexts to all lowercase to match database
        String searchText = searchTexts.toLowerCase();

        // Separate the search text into individual keyword(e.g.['tennis', 'gay'])
        String[] searchTextList = searchText.split(",");

        // Create a variable to store rows that contains the searchText
        List<Row> filteredRows = new ArrayList<Row>();
        ;

        // Iterate through the keywords
        for (int i = 0; i < searchTextList.length; i++) {
            //Iterate rows
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

                HSSFRow row = sheet.getRow(j);

                // Handle when searchText is double
                Double doubleValue = null;
                try {
                    doubleValue = Double.parseDouble(searchTextList[i]);
                } catch (Exception e) {
                }

                // Iterate columns
                for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                    HSSFCell cell = row.getCell(k);

                    // Handle empty cells
                    if (cell == null) {
                        continue;
                    }

                    // Search based on cell types (String OR Numeric)
                    switch (cell.getCellType()) {

                        // Handle cell with String values
                        case STRING:
                            if (searchTextList[i] != null && searchTextList[i].equals(cell.getStringCellValue())) {
                                filteredRows.add(row);
                            }
                            break;


                        // Handle cell with numeric values
                        case NUMERIC:
                            if (doubleValue != null && doubleValue.doubleValue() == cell.getNumericCellValue()) {
                                filteredRows.add(row);
                            }
                            break;
                    }
                }
            }
        }

        // Only keep rows that has been duplicated for n(number of keywords) times(i.e. keep rows that matched with
        // all keywords
        // dup is the filter list that contains only users satisfies all keywords entered
        List<Row> dup = new ArrayList<Row>();
        ;
        int numberOfKeyword = searchTextList.length;

        Map<Row, Long> occurrences = filteredRows.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        occurrences.values().removeIf(v -> v < numberOfKeyword);
        for (Row key : occurrences.keySet()) {
            dup.add(key);
        }

        // Create a sub-arraylist that contains 20 of the matched users along with their corresponding profiles
        List<Row> twentyMatchedUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        if (dup.size() != 0) {
            List<Row> uniqueDup = dup
                    .stream() // get stream for original list
                    .distinct() // distinct method removes duplicates
                    .collect(Collectors.toList());

            // Make sure we only count the at most 20 users that satisfied features identified from keywords entered
            if (dup.size() > 20) {
                for (int i = 0; i < 20; i++) {
                    twentyMatchedUsers.add(uniqueDup.get(i));
                }
            }
            else{twentyMatchedUsers = uniqueDup;}

            for (Row row : twentyMatchedUsers) {
                Profile profile = profileGateway.CreateProfile(row);
                String usrname = row.getCell(8).toString();
                String password = row.getCell(9).toString();
                String isVip = row.getCell(10).toString();
                List<String> liked = likesGateway.findLiked(usrname);
                List<String> likedme = likesGateway.findLikedMe(usrname);
                Hashtable<Integer, List<Object>> reviews = reviewGateway.getReviews(usrname);
                User user;
                if (isVip.equals("TRUE")) {
                    user = new VipUser(password, usrname, profile, true, liked, likedme, reviews);
                } else {
                    user = new RegularUser(password, usrname, profile, liked, likedme, reviews);
                }
                users.add(user);
            }
        }
        //save twenty matched users as seen for recommend usage
        this.SaveSeen(username, users);

        //return the twenty matched users in the form of List<User>
        return users;
    }

    @Override
    public String getWorkingDir(){
        return super.workingdir;
    }
    @Override
    public void SaveSeen(String username, List<User> usersviewed) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);

        for (User userviewed:usersviewed){
            if (likesGateway.isSeen(username, userviewed.getAccountName())){
                return;
            }
            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
            row.createCell(0).setCellValue(username);
            row.createCell(1).setCellValue(userviewed.getAccountName());
            row.createCell(2).setCellValue(0);
        }
        SaveWorkbook(wb,"likes");
    }

}




