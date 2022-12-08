package use_cases.regular_user_register_use_case;

public class UserRegisterResponseModel {

    String login;
    String creationTime;

    /**
     * @param login the accountname of user
     * @param creationTime the create time of account
     */
    public UserRegisterResponseModel(String login, String creationTime) {
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
