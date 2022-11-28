<<<<<<< HEAD:src/main/java/use_cases/Search/Search.java
package use_cases.Search;
=======
>>>>>>> ae58744050e1c59bbef866ff07acf2b7789d7edb:src/main/java/use_cases/search_use_case/Search.java
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Search {

        public ArrayList<Row> searchSheet(String searchTexts, HSSFSheet sheet) {

            // Convert searchTexts to all lowercase to match database
            String searchText = searchTexts.toLowerCase();

            // Separate the search text into individual keyword(e.g.['tennis', 'gay'])
            String[] searchTextList = searchText.split(",");

            // Create a variable to store rows that contains the searchText
            ArrayList<Row> filteredRows = new ArrayList<>();

            // Iterate through the keywords
            for (int i=0;i<searchTextList.length;i++){
                //Iterate rows
                for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

                    HSSFRow row = sheet.getRow(j);

                    // Handle when searchText is double
                    Double doubleValue = null;
                    try {
                        doubleValue = Double.parseDouble(searchTextList[i]);
                    } catch(Exception e) {
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
            ArrayList<Row> dup = new ArrayList<>();
            int numberOfKeyword = searchTextList.length;

            Map<Row, Long> occurrences = filteredRows.stream()
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()));
            occurrences.values().removeIf(v -> v < numberOfKeyword);
            for (Row key : occurrences.keySet() ) {
                dup.add(key);
            }
            // Create a sub-arraylist that contains 20 of the matched users along with their corresponding profiles
            ArrayList<Row> first20ofDup = new ArrayList<>();
            for (int i=0;i<21;i++){
                first20ofDup.add(dup.get(i));
            }

            // return the 20 matched users along with their corresponding profiles
            return first20ofDup;
        }
    }


