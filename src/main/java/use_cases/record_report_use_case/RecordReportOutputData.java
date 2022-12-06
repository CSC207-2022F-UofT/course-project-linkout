package use_cases.record_report_use_case;

public class RecordReportOutputData {
    private String timestamp;

    /**
     * The constructor instantiating an output data object containing the timestamp
     * corresponding to the report's creation.
     */
    public RecordReportOutputData() {
        timestamp = "";
    }

    /*
    Getters and setters.
     */
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
