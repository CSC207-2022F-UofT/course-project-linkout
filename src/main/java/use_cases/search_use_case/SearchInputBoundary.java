
package use_cases.search_use_case;
import javax.management.InvalidAttributeValueException;
import java.io.IOException;

public interface SearchInputBoundary {
    SearchResponseModel search(SearchRequestModel requestModel) throws IOException, InvalidAttributeValueException;
}