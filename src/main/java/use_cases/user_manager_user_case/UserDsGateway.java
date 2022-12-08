package use_cases.user_manager_user_case;

import entities.User;

public interface UserDsGateway {

    public boolean existByName(String name);

    public User findUser(String name);

    public void upgrade(String accName, boolean status);
}
