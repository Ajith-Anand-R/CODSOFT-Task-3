import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.HashMap;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String dataFile = "atm_data.json";

        // Load users from JSON if file exists
        try (Reader reader = new FileReader(dataFile)) {
            HashMap<String, User> loadedUsers = gson.fromJson(reader, new TypeToken<HashMap<String, User>>(){}.getType());
            if (loadedUsers != null && !loadedUsers.isEmpty()) {
                bank.setUsers(loadedUsers);
            } else {
                // If file is empty or has no users, add default users
                bank.addUser(new User("user1", "1234", 1000.0));
                bank.addUser(new User("user2", "5678", 500.0));
                bank.addUser(new User("user3", "4321", 2000.0));
                bank.addUser(new User("user4", "8765", 1500.0));
            }
        } catch (IOException e) {
            // File not found, start with default users
            bank.addUser(new User("user1", "1234", 1000.0));
            bank.addUser(new User("user2", "5678", 500.0));
            bank.addUser(new User("user3", "4321", 2000.0));
            bank.addUser(new User("user4", "8765", 1500.0));
        }

        System.out.println("Welcome to the ATM!");
        User currentUser = null;
        while (currentUser == null) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            currentUser = bank.authenticate(userId, pin);
            if (currentUser == null) {
                System.out.println("Invalid credentials. Please try again.\n");
            }
        }

        int choice = 0;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Please enter a valid choice.");
                continue;
            }
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    currentUser.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (currentUser.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    if (depositAmount > 0) {
                        currentUser.deposit(depositAmount);
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case 4:
                    System.out.print("Enter recipient User ID: ");
                    String recipientId = scanner.nextLine();
                    User recipient = bank.getUserById(recipientId);
                    if (recipient == null || recipient == currentUser) {
                        System.out.println("Invalid recipient.");
                        break;
                    }
                    System.out.print("Enter amount to transfer: $");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    if (currentUser.transfer(recipient, transferAmount)) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
        // Save users to JSON on exit
        try (Writer writer = new FileWriter(dataFile)) {
            gson.toJson(bank.getUsers(), writer);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
} 