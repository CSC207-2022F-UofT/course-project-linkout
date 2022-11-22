package record_review_use_case;
import UI.RecordReportResultViewModel;

import java.text.SimpleDateFormat;

public class RecordReportPresenter implements RecordReportOutputBoundary {
    RecordReportOutputData recordReportOD;
    RecordReportResultViewModel recordReportRVM;

    public RecordReportPresenter(RecordReportOutputData recordReportOD,
                                 RecordReportResultViewModel recordReportRVM) {
        this.recordReportOD = recordReportOD;
        this.recordReportRVM = recordReportRVM;
    }

    @Override
    public RecordReportOutputData responseView(boolean success) {
        if (success) recordReportRVM.successView();
        else recordReportRVM.failView();
        this.setTimestamp();
        return recordReportOD;
    }

    public void setTimestamp() {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        recordReportOD.setTimestamp(timestamp);
    }
}
