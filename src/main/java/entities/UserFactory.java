package entities;

public class UserFactory {
    public User create(String password, String accountName, Profile profile, boolean isVip){
        return new VipUser(password, accountName, profile, true);
    }

    public User create(String password, String accountName, Profile profile){
        return new RegularUser(password, accountName, profile);
    }

}
