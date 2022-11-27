package entities;

public interface AccountRegister {
    boolean passwordIsValid();

    String getAccountName();

    String getPassword();
}
