package use_cases.user_action_use_case;

// Interface Adapter Layer
public class UserActPresenter implements UserActPresenterInterface {


    @Override
    public String prepareSuccessView(String targetName) {
        return "Successfully Liked " + targetName + " !";
    }

    @Override
    public String prepareMatchingView(String targetName) {
        return "Matched with " + targetName + " !";
    }

    @Override
    public String prepareFailView(String error) {
        throw new UserActionFailed(error);
    }
}
