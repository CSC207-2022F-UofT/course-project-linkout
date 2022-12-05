package entities;

public class RegUserFactory implements AllUserFactory{
    public User create(String password, String accountName, Profile profile){
        return new RegularUser(password, accountName, profile);
    }
}
