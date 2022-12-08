package use_cases.record_report_use_case;
import entities.Admin;
import entities.User;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface RecordReportGateway {
    User findUser(String userID) throws IOException, InvalidAttributeValueException;
    Admin findAdmin(String adminID);
}
