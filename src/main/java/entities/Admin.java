package entities;

public class Admin extends Account implements AdminFactory {
    public Admin (String password, String accountName) {
        super(password, accountName);
    }

    @Override
    public boolean giveWarning() {
        return false;
    }

    @Override
    public boolean restrictUser() {
        return false;
    }

    public void addMessage(String message) { System.out.println(message); }
}
