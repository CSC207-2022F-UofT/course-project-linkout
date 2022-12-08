package screens.user_login;

public class UserLoginFailed extends RuntimeException {
    public UserLoginFailed(String error) {
        super(error);
    }
}
