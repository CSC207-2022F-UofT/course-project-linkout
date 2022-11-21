package user_action_use_case;

public interface UserActDsGateway {
    public void save(UserActDsRequestModel requestModel);
    public boolean existByName(String accName, String targetName, boolean isLiking);
}
