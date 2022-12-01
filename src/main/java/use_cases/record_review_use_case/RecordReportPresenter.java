package use_cases.record_review_use_case;
import screens.record_report.RecordReportResultViewModel;

import java.text.SimpleDateFormat;

public class RecordReportPresenter implements RecordReportOutputBoundary {
    RecordReportOutputData recordReportOD;
    RecordReportResultViewModel recordReportRVM;

    /**
     * Constructor for the presenter class.
     * @param recordReportOD    An instance of the output data.
     * @param recordReportRVM   An instance of the view model.
     */
    public RecordReportPresenter(RecordReportOutputData recordReportOD,
                                 RecordReportResultViewModel recordReportRVM) {
        this.recordReportOD = recordReportOD;
        this.recordReportRVM = recordReportRVM;
    }

    /**
     * Calls the success view of the use case view model if success is true and the fail view
     * of the it if success is false. Returns the instance of the recordReportOutputBoundary.
     * @param success This is the success boolean variable.
     * @return recordReportOD This is the instance of the recordReportOutputBoundary.
     */
    @Override
    public RecordReportOutputData responseView(boolean success) {
        if (success) recordReportRVM.successView();
        else recordReportRVM.failView();
        this.setTimestamp();
        return recordReportOD;
    }

    /**
     * Assigns a timestamp to the instance of the recordReportOutputBoundary.
     */
    public void setTimestamp() {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        recordReportOD.setTimestamp(timestamp);
    }
}
