import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit of " + amount + " successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal of " + amount + " successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

public class ATMSimulation {
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        initializeAccounts();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            Account account = accounts.get(accountNumber);
            if (account.validatePin(pin)) {
                System.out.println("Login successful.");
                displayMenu(scanner, account);
            } else {
                System.out.println("Invalid PIN.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void initializeAccounts() {
        // Sample accounts
        accounts.put("123456", new Account("123456", "1234", 1000));
        accounts.put("789012", new Account("789012", "5678", 5000));
    }

    private static void displayMenu(Scanner scanner, Account account) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance(account);
                    break;
                case 2:
                    deposit(scanner, account);
                    break;
                case 3:
                    withdraw(scanner, account);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance(Account account) {
        System.out.println("Current balance: " + account.getBalance());
    }

    private static void deposit(Scanner scanner, Account account) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private static void withdraw(Scanner scanner, Account account) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}
