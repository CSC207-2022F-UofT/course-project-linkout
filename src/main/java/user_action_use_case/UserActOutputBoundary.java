package user_action_use_case;

public interface UserActOutputBoundary {
    String prepareSuccessView(String accName);
    String prepareMatchingView(String targetName);
    String prepareFailView(String error);
}
