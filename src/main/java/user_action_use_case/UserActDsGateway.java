package user_action_use_case;

import entities.User;

import java.io.IOException;

public interface UserActDsGateway {
    public void setLike(String accName, String targetName) throws IOException;
    public boolean isLiked(String accName, String targetName) throws IOException;

    public User findUser(String accName);

}
