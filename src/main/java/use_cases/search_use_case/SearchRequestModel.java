package use_cases.search_use_case;

import entities.User;


public class SearchRequestModel {
    private String keywords;
    private String username;

    /**
     * Create a new SearchRequestModel with keywords entered as the input data. 
     * Preconditions: 
     * (1) keywords entered is not empty
     * (2) keywords entered is in the form that each feature intereted is separated by comma (e.g. "22,tennnis,straight")

     * @param keywords the keywords entered
     * @param username the name of the user who initiates search 
     */


    public SearchRequestModel(String keywords, Strinf username) {
        this.keywords = keywords;
        this.username = user.getAccountName();
    }

      public String getUsername(){
        return username;
    }