package user_action_use_case;

public interface UserActPresenterInterface {
    public String prepareSuccessView(String targetName);
    public String prepareMatchingView(String targetName);
    public String prepareFailView(String error);
}
