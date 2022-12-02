package use_cases.user_action_use_case;

// use case layer
public interface UserActPresenterInterface {
    String prepareSuccessView(String targetName);
    String prepareMatchingView(String targetName, String contactInfo);
    String prepareFailView(String error);
}
