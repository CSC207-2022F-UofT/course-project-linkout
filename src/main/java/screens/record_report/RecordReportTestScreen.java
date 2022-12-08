package screens.record_report;

public class RecordReportTestScreen implements RecordReportResultViewModel {
    @Override
    public void successView() { System.out.println("Test passed"); }

    @Override
    public void failView() { System.out.println("Test failed"); }
}
