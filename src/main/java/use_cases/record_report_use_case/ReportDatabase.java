package use_cases.record_report_use_case;

import Gateway.DatabaseGateway;
import entities.Report;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ReportDatabase extends DatabaseGateway implements ReportDatabaseGateway {
    /**
     * The constructor for the database.
     * @param w     The directory start string.
     */
    public ReportDatabase(String w) { super(w); }

    /**
     * This can save a report to the database (in an .xls file).
     * @param report                            The report object.
     */
    @Override
    public void saveReport(Report report) {
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

    /**
     * The method to get a report from the database.
     * @param reportID                          The ID of the report.
     * @return                                  The report to be accessed.
     */
    @Override
    public Report getReport(String reportID) {
        try {
            HSSFWorkbook wb = ReportsBook();
            HSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                int currid = loadIntCell(sheet.getRow(i).getCell(2));
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
