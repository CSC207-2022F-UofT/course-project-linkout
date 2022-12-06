package use_cases.user_use_case;

import entities.User;

public interface UserDsGateway {

    public boolean existByName(String name);

    public User findUser(String name);

    public void upgrade(String accName, boolean status);
}
