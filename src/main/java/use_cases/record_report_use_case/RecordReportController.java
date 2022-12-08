package use_cases.record_report_use_case;

public class RecordReportController {
    RecordReportInputBoundary recordReportIB;

    /**
     * Constructor
     * @param recordReportIB
     */
    public RecordReportController(RecordReportInputBoundary recordReportIB) {
        this.recordReportIB = recordReportIB;
    }

    /**
     * Creates a report from the specified parameters. It is passed to the interactor.
     * @param r1    The ID of the user who made the report.
     * @param r2    The ID of the user which the report concerns.
     * @param c     The category of the report.
     * @param t     The text of the report.
     * @param se    Any supporting evidence (e.g., links to images) for the report.
     * @return recordReportIB
     */
    public RecordReportOutputData createReport(String r1, String r2, String c,
                                               String t, String se) {
        RecordReportInputData recordReportID = new RecordReportInputData(r1, r2, c, t, se);
        return recordReportIB.createReport(recordReportID);
    }
}
