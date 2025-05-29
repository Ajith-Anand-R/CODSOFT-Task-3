# Java ATM Console Application

## Overview
This is a simple console-based ATM application written in Java. It simulates basic ATM functionalities such as user authentication, transaction history, withdrawal, deposit, and fund transfer between users.

## Features
- User login with user ID and PIN
- View transaction history
- Withdraw money
- Deposit money
- Transfer funds to another user
- Multiple test users for easy testing

## Setup & Running
1. **Navigate to the project directory:**
   ```sh
   cd Task-3/Task-3
   ```
2. **Compile all Java files:**
   ```sh
   javac *.java
   ```
3. **Run the application:**
   ```sh
   java ATM
   ```

## Test Users
You can log in with any of the following users:

| User ID | PIN  | Initial Balance |
|---------|------|-----------------|
| user1   | 1234 | $1000           |
| user2   | 5678 | $500            |
| user3   | 4321 | $2000           |
| user4   | 8765 | $1500           |

## Usage
- After login, follow the on-screen menu to perform ATM operations.
- For transfers, enter the recipient's User ID and the amount.
- Transaction history will show all your past actions.

## Notes
- All data is stored in memory and will reset when the program exits.
- You can add more users in the `ATM.java` file for further testing.

## Persistent Transaction Storage
This application can save and load all user data and transactions in a JSON file using the Gson library.

### How to Enable Persistence
1. **Download Gson:**
   - Download `gson-2.8.9.jar` (or latest) from: https://github.com/google/gson
   - Place the JAR file in your `Task-2/Task-2` directory.
2. **Compile with Gson:**
   ```sh
   javac -cp .;gson-2.8.9.jar *.java
   ```
3. **Run with Gson:**
   ```sh
   java -cp .;gson-2.8.9.jar ATM
   ```

The app will automatically save all users and transactions to `atm_data.json` on exit, and load them on startup.
