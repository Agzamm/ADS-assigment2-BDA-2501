import java.util.LinkedList;
import java.util.Scanner;

class BankAccount {
    int number;
    String username;
    double balance;
    static History history = new History();
    static BillQueue billQueue = new BillQueue();
    static AccountQueue accountQueue = new AccountQueue();

    public BankAccount(int number, String username, double balance) {
        this.number = number;
        this.username = username;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        String transaction = "Deposit " + amount;
        history.add(transaction);
        System.out.println("Deposit successful! " + transaction);
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance! Available: " + String.format("%.2f", balance));
        } else {
            balance -= amount;
            String transaction = "Withdrawal " + amount;
            history.add(transaction);
            System.out.println("Withdrawal successful!");
        }
    }

    void payBill(double amount, String billName) {
        if (amount > balance) {
            System.out.println("Insufficient balance! Available: " + String.format("%.2f", balance));
        } else {
            balance -= amount;
            String transaction = "Bill " + amount + " for " + billName;
            history.add(transaction);
            System.out.println("Bill payment successful: " + transaction);
        }
    }

    void undoLastTransaction() {
        String last = history.getLast();
        if (last == null) {
            System.out.println("No transactions to undo");
            return;
        }

        String[] parts = last.split(" ");
        double amount = Double.parseDouble(parts[1]);
        if (last.startsWith("Deposit")) {
            balance -= amount;
        } else if (last.startsWith("Withdrawal") || last.startsWith("Bill")) {
            balance += amount;
        }

        history.undo();
    }

    void showLastTransaction() {
        history.showLast();
    }

    @Override
    public String toString() {
        return username + " - Balance: " + String.format("%.2f", balance);
    }
}

public class Bank {
    static Scanner sc = new Scanner(System.in);
    static LinkedList<BankAccount> accounts = new LinkedList<>();

    // Safely read an integer from input, keeps asking until valid
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    // Safely read a double from input, keeps asking until valid
    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        int choice;

        while (true) {
            System.out.println("1. Add New Account");
            System.out.println("2. Display All Accounts");
            System.out.println("3. Search Account by Username");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Pay Bill");
            System.out.println("7. Undo Last Transaction");
            System.out.println("8. Show Last Transaction");
            System.out.println("9. Bill Payment Queue");
            System.out.println("10.Account Queue");
            System.out.println("0. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    displayAccounts();
                    break;
                case 3:
                    searchByUsername();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    withdrawMoney();
                    break;
                case 6:
                    payBill();
                    break;
                case 7:
                    undoTransaction();
                    break;
                case 8:
                    showLastTransaction();
                    break;
                case 9:
                    BankAccount.billQueue.menu();
                    break;
                case 10:
                    accountQueueMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addAccount() {
        int number = readInt("Enter account number: ");
        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();
        double balance = readDouble("Enter initial balance: ");

        accounts.add(new BankAccount(number, username, balance));
        System.out.println("Account added successfully!");
    }

    private static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
            return;
        }
        System.out.println("Accounts List:");
        for (BankAccount acc : accounts) {
            System.out.println(acc);
        }
    }

    private static void searchByUsername() {
        System.out.print("Enter username to search: ");
        String username = sc.nextLine().trim();

        boolean found = false;
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                System.out.println("Account Found: " + acc);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No account found with username: " + username);
        }
    }

    private static void depositMoney() {
        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                double amount = readDouble("Enter deposit amount: ");
                acc.deposit(amount);
                return;
            }
        }
        System.out.println("Account not found");
    }

    private static void withdrawMoney() {
        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                double amount = readDouble("Enter withdrawal amount: ");
                acc.withdraw(amount);
                return;
            }
        }
        System.out.println("Account not found");
    }

    private static void undoTransaction() {
        System.out.print("Enter username: ");
        String name = sc.nextLine().trim();
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(name)) {
                acc.undoLastTransaction();
                return;
            }
        }
        System.out.println("Account not found");
    }

    private static void showLastTransaction() {
        System.out.print("Enter username: ");
        String name = sc.nextLine().trim();
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(name)) {
                acc.showLastTransaction();
                return;
            }
        }
        System.out.println("Account not found");
    }

    private static void payBill() {
        System.out.print("Enter username: ");
        String name = sc.nextLine().trim();
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(name)) {
                System.out.print("Bill name: ");
                String bill = sc.nextLine().trim();
                double amount = readDouble("Amount: ");
                acc.payBill(amount, bill);
                return;
            }
        }
        System.out.println("Account not found");
    }

    static void accountQueueMenu() {
        while (true) {
            System.out.println("--- Account Opening Queue (Admin) ---");
            System.out.println("1.Submit Request");
            System.out.println("2.Process Next (Admin)");
            System.out.println("3.Display Pending");
            System.out.println("0.Back");
            switch (readInt("Choice: ")) {
                case 1: {
                    int n = readInt("Account number: ");
                    System.out.print("Username: ");
                    String u = sc.nextLine().trim();
                    double b = readDouble("Initial balance: $");
                    BankAccount.accountQueue.addRequest(n, u, b);
                    break;
                }
                case 2:
                    BankAccount.accountQueue.processNext(accounts);
                    break;
                case 3:
                    BankAccount.accountQueue.displayPending();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}