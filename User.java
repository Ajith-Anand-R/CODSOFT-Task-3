import java.util.ArrayList;

public class User {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Getters and setters will be added here
    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void printTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction(new Transaction("Withdraw", amount, new java.util.Date()));
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction(new Transaction("Deposit", amount, new java.util.Date()));
        }
    }

    public boolean transfer(User recipient, double amount) {
        if (recipient != null && amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            addTransaction(new Transaction("Transfer to " + recipient.getUserId(), amount, new java.util.Date()));
            recipient.addTransaction(new Transaction("Transfer from " + this.getUserId(), amount, new java.util.Date()));
            return true;
        }
        return false;
    }
} 