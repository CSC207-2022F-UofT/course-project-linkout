package use_cases.user_action_use_case;

// Interface Adapter Layer
public class UserActionFailed extends RuntimeException{
    public UserActionFailed(String error){super(error);}
}
