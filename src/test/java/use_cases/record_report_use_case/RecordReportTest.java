package use_cases.record_report_use_case;

import entities.Report;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordReportTest {
    @Test
    public void createReport() {
        ArrayList se = new ArrayList<String>();
        se.add("Oh no.");
        Report r1 = new Report("Username 1", "Username 2",
                "Bullying or harassment", "I don't like username 2.",
                se);
        assertEquals(r1.getReportText(), "I don't like username 2.");

        se = new ArrayList<String>();
        se.add("I don't like username 2.");
        se.add("Oh no.");

        Report r2 = new Report("Username 1", "Username 2", "12", "Bullying or harassment", se);
        //assertEquals(r2.getCategory(), "Bullying or harassment");
        //assertEquals(r2.getReportID(), "12");
        assertEquals(r2.getSupportingEvidence(), se);
    }
    @Test
    void recordReport() {
        //ReportGa
    }
}
