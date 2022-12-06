package use_cases.user_login_use_case;

public class UserLoginRequestModel {
    private final String username;
    private final String password;

    public UserLoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
