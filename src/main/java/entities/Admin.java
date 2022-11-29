package entities;

public class Admin extends Account {
    public Admin (String password, String accountName) {
        super(password, accountName);
    }

    public void addMessage(String message) { System.out.println(message); }
}