import java.util.HashMap;

public class Bank {
    private HashMap<String, User> users;

    public Bank() {
        users = new HashMap<>();
    }

    public User authenticate(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    // Methods to add users, etc., can be added here
} 