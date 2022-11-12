package record_review_use_case;
import java.util.ArrayList;

public class RecordReportController {
    RecordReportInputBoundary recordReportIB; // Should this be final?

    public RecordReportController(RecordReportInputBoundary recordReportIB) {
        this.recordReportIB = recordReportIB;
    }

    public RecordReportOutputData createReport(String r1, String r2, String c,
                                               String t, ArrayList<String> se) {
        RecordReportInputData recordReportID = new RecordReportInputData(r1, r2, c, t, se);
        return recordReportIB.createReport(recordReportID);
    }
}
