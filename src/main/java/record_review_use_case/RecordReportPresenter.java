package record_review_use_case;

public class RecordReportPresenter implements RecordReportOutputBoundary {
    RecordReportOutputData recordReportOD; // Should this be final?

    public RecordReportPresenter(RecordReportOutputData recordReportOD) {
        this.recordReportOD = recordReportOD;
    }

    @Override
    public RecordReportOutputData showResult(boolean success) {
        if (success) {
            // Let ReportFrame show a pop-up message
        } else {
            // also show pop up but with FAILURE!!!
        }
        return recordReportOD; // Investigate whether outputdata is needed;
    }
}
