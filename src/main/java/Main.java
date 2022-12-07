
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import use_cases.recommend_use_case.RecommendGateway;
import use_cases.review_use_case.ReviewGatewayImplementation;

import javax.management.InvalidAttributeValueException;
import java.io.*;

public class Main {

    public static void removeRow(HSSFSheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        Row removingRow=sheet.getRow(rowIndex);
        if (removingRow != null) {
            sheet.removeRow(removingRow);
        }
        if (rowIndex>=0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }

    }

    public static void initializeDataset() throws IOException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Reinitiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/likes.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/likes.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        String[] command3 = {"rm", String.format("%s/src/main/data/reviews.xls", db.getWorkingDir())};
        String[] command4 = {"cp", String.format("%s/src/main/data/data_storage/reviews.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2);
        runtime.exec(command3);
        runtime.exec(command4);
    }

    public static void trymethod() throws IOException, InvalidAttributeValueException {
        FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/likes.xls"));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);

        System.out.println(wb);
        removeRow(sheet, 1);
        System.out.println(wb);
        fis.close();
        FileOutputStream fisout=new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/data/trys.xls"));
        wb.write(fisout);
        fisout.close();
    }

    public static void main(String[] args) throws IOException, InvalidAttributeValueException {

        initializeDataset();

        //ReviewGatewayImplementation reviewGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        //reviewGateway.removeReview(3728, "acc1");
        //trymethod();

    }
}
