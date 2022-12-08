package use_cases.user_login_use_case;

public class UserLoginResponseModel {
    String login;
    String creationTime;

    /**
     * @param login the account name
     * @param creationTime the time of person login
     */
    public UserLoginResponseModel(String login, String creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
