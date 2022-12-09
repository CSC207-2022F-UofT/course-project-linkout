package use_cases.search_use_case;


public class SearchRequestModel {

    private final String keywords;
    private final String username;

    /**
     * Create a new SearchRequestModel with valid keywords entered by the user as the input data.
     * <p>
     * Preconditions:
     * (1) keywords entered is not empty;
     * (2) keywords entered is in the form that each feature intereted is separated by comma (e.g. "tennnis, 22, straight")
     *
     * @param keywords the valid keywords entered by the user
     * @param username the user who is searching
     */

    public SearchRequestModel(String keywords, String username) {
        this.keywords = keywords;
        this.username = username;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getUsername() {
        return username;
    }
}