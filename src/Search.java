package Search;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;


public class Search {
    public ArrayList<Row> searchSheet(String searchTexts, HSSFSheet sheet) {

        // Create a variable to store rows that contains the searchText
        ArrayList<Row> filteredRows = new ArrayList<>();

        // Convert searchTexts to all lowercase to match database
        String searchText = searchTexts.toLowerCase();

        //Iterate rows
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {

            HSSFRow row = sheet.getRow(j);

            // Handle when searchText is double
            Double doubleValue = null;
            try {
                doubleValue = Double.parseDouble(searchText);
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
                        if (searchText != null && searchText.equals(cell.getStringCellValue())) {
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
        // return an arraylist that contais the matched users along with their corresponding profiles
        return filteredRows;
    }
}


