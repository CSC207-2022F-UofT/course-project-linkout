
package use_cases.search_use_case;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface SearchInputBoundary {
    SearchResponseModel searchSheet(SearchRequestModel requestModel) throws IOException, InvalidAttributeValueException;
}