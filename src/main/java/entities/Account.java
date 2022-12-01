package entities;

abstract public class Account implements AccountRegister{
    private String password;
    private String accountName;

    /**
     * Create a new Account with password and accountName.
     *
     * @param password the password of this single Account.
     * @param accountName the accountName of this single Account.
     */

    public Account(String password, String accountName) {
        this.password = password;
        this.accountName = accountName;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean passwordIsValid() {
        return password != null && password.length() > 3;
    }
}
