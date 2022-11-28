package use_cases.search_use_case;

import entities.User;


public class SearchRequestModel {
    
    private String keywords;

    /**
     * Create a new SearchRequestModel with valid keywords entered by the user as the input data. 

     * Preconditions: 
     * (1) keywords entered is not empty;
     * (2) keywords entered is in the form that each feature intereted is separated by comma (e.g. "tennnis, 22, straight")

     * @param keywords the valid keywords entered by the user
     */

    public SearchRequestModel(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }
    }