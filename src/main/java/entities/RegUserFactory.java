package entities;

public class RegUserFactory {
    public User creat(String password, String accountName, Profile profile){
        return new RegularUser(password, accountName, profile);
    }
}
