package entities;

public interface AllUserFactory {
    public User create(String password, String accountName, Profile profile);
}
