package use_cases.user_action_use_case;

// use case layer
public interface UserActPresenterInterface {
    public String prepareSuccessView(String targetName);
    public String prepareMatchingView(String targetName);
    public String prepareFailView(String error);
}
