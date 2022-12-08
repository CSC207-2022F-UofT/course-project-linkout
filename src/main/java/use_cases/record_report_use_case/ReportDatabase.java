package use_cases.record_report_use_case;

import Gateway.DatabaseGateway;
import entities.Report;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public class ReportDatabase extends DatabaseGateway implements ReportDatabaseGateway {
    public ReportDatabase(String w) { super(w); }
    @Override
    public void saveReport(Report report) throws IOException, InvalidAttributeValueException {
        try {
            HSSFWorkbook w = ReportsBook();
            HSSFSheet sheet = w.getSheetAt(0);

            Row rowReport = sheet.createRow(sheet.getPhysicalNumberOfRows());
            rowReport.createCell(0).setCellValue(report.getReportingUserID());
            rowReport.createCell(1).setCellValue(report.getReportedUserID());
            rowReport.createCell(2).setCellValue(report.getReportID());
            rowReport.createCell(3).setCellValue(report.getCategory());
            rowReport.createCell(4).setCellValue(report.getReportText());
            rowReport.createCell(5).setCellValue(report.getSupportingEvidence());

            SaveWorkbook(w, "reports");
        } catch (Exception e) {
            System.out.println("Something happened with ReportDatabase.");
        }
    }

    @Override
    public Report getReport(String reportID) throws IOException, InvalidAttributeValueException {
        try {
            HSSFWorkbook wb = ReportsBook();
            HSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                int currid = loadIntCell(sheet.getRow(i).getCell(0));
                if (currid == Integer.parseInt(reportID)) {
                    Row row = sheet.getRow(i);
                    return new Report(
                            loadStringCell(row.getCell(0)),
                            loadStringCell(row.getCell(1)),
                            loadStringCell(row.getCell(2)),
                            loadStringCell(row.getCell(3)),
                            loadStringCell(row.getCell(4)),
                            loadStringCell(row.getCell(5))
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Something happened with ReportDatabase.");
        }

        return new Report("Error or requested report doesn't exist.", "", "", "", "", "");
    }
}
