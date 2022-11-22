package use_cases;

import org.junit.Test;
import static org.junit.Assert.*;

import entities.*;

import java.util.ArrayList;
import java.util.Collections;


public class ReportUserTest {
    @Test
    public void TestCreatingReport() {
        Report report = new Report("User 1", "User 2", "Bullying",
                "User 2 bullied me", new ArrayList<>(Collections.emptyList()));
        assertEquals(report.getReportedUserID(), "User 2");
    }

    public void TestAddingReport() {}
    public void TestAddReportSuccessMessage() {}
}
