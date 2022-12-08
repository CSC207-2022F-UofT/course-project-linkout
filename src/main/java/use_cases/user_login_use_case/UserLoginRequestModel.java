package use_cases.user_login_use_case;

/**
 *
 */
public class UserLoginRequestModel {
    private final String username;
    private final String password;

    /**
     * @param username the username of account
     * @param password the password of account
     */
    public UserLoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

}
