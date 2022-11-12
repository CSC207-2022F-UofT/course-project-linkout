package entities;
import java.util.ArrayList;
import java.util.List;

abstract class User {
    private final List<Report> reports = new ArrayList<Report>();
    void addReport(Report report) {
        reports.add(report);
    }
}