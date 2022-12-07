package use_cases.user_login_use_case;

public interface UserLoginDsGateway {
    boolean MatchingNameAndPassword(String accountName, String Password);
    boolean NotExist(String accountName);
}
