package models;

public class Admin extends User {

    public Admin(String username) {
        super(username);
    }

    public void showAdminPanel() {
        System.out.println("Admin controls activated");
    }
}
