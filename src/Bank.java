import java.util.Scanner;

class BankAccount {
    int    accountNumber;
    String username;
    double balance;

    BankAccount(int accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

class MyLinkedList {

    static class Node {
        BankAccount data;
        Node        next;

        Node(BankAccount data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    void addAccount(int accountNumber, String username, double balance) {
        Node newNode = new Node(new BankAccount(accountNumber, username, balance));

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Account added successfully");
    }

    void displayAll() {
        if (head == null) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("Accounts List:");
        int  i       = 1;
        Node current = head;
        while (current != null) {
            System.out.println(i + ". " + current.data.username
                    + " - Balance: " + (int) current.data.balance);
            i++;
            current = current.next;
        }
    }

    Node searchByUsername(String username) {
        Node current = head;
        while (current != null) {
            if (current.data.username.equalsIgnoreCase(username)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    void deposit(String username, double amount) {
        Node node = searchByUsername(username);
        if (node == null) {
            System.out.println("Account not found.");
            return;
        }
        node.data.balance += amount;
        System.out.println("Deposit: " + (int) amount);
        System.out.println("New balance: " + (int) node.data.balance);
    }

    void withdraw(String username, double amount) {
        Node node = searchByUsername(username);
        if (node == null) {
            System.out.println("Account not found.");
            return;
        }
        if (amount > node.data.balance) {
            System.out.println("Insufficient balance. Available: " + (int) node.data.balance);
            return;
        }
        node.data.balance -= amount;
        System.out.println("Withdraw: " + (int) amount);
        System.out.println("New balance: " + (int) node.data.balance);
    }
}

class MyStack {
    private String[] arr;
    private int capacity;
    private int top;

    MyStack(int cap) {
        capacity = cap;
        arr = new String[capacity];
        top = -1;
    }

    void push(String value) {
        if (top == capacity - 1) {
            System.out.println("Stack is full.");
            return;
        }
        arr[++top] = value;
    }

    String pop() {
        if (isEmpty()) return null;
        String value = arr[top];
        arr[top]     = null;
        top--;
        return value;
    }

    String peek() {
        if (isEmpty()) return null;
        return arr[top];
    }

    boolean isEmpty() {
        return top == -1;
    }
}

class MyQueue {

    class Node {
        String data;
        Node   next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    MyQueue() {
        front = rear = null;
    }

    // enqueue: add bill to the back
    void enqueue(String bill) {
        Node newNode = new Node(bill);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Added: " + bill);
    }

    String dequeue() {
        if (isEmpty()) return null;
        String value = front.data;
        front        = front.next;
        if (front == null) rear = null;
        return value;
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        System.out.println("Pending Bills:");
        Node current = front;
        int  i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data);
            i++;
            current = current.next;
        }
    }

    boolean isEmpty() {
        return front == null;
    }
}

class AccountQueue {

    class Node {
        BankAccount data;
        Node next;

        Node(BankAccount data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    AccountQueue() {
        front = rear = null;
    }

    void submitRequest(int accountNumber, String username, double balance) {
        Node newNode = new Node(new BankAccount(accountNumber, username, balance));
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Request submitted for: " + username);
    }

    void processNext(MyLinkedList accounts) {
        if (isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        BankAccount approved = front.data;
        front = front.next;
        if (front == null) rear = null;

        accounts.addAccount(approved.accountNumber, approved.username, approved.balance);
        System.out.println("Approved and added: " + approved.username + " - Balance: " + (int) approved.balance);
    }

    void displayPending() {
        if (isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        System.out.println("Pending Requests:");
        Node current = front;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data.username + " - Balance: " + (int) current.data.balance);
            i++;
            current = current.next;
        }
    }

    boolean isEmpty() {
        return front == null;
    }
}

public class Bank {

    static Scanner sc = new Scanner(System.in);
    static MyLinkedList accounts = new MyLinkedList();
    static MyStack history = new MyStack(100);
    static MyQueue billQueue = new MyQueue();
    static AccountQueue accountQueue = new AccountQueue();

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try   { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid input."); }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try   { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid input."); }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Account");
            System.out.println("2. Display All Accounts");
            System.out.println("3. Search by Username");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Transaction History");
            System.out.println("7. Bill Payment Queue");
            System.out.println("8. Account Opening Queue (Admin)");
            System.out.println("0. Exit");

            switch (readInt("Choice: ")) {
                case 1 -> addAccount();
                case 2 -> accounts.displayAll();
                case 3 -> searchAccount();
                case 4 -> depositMenu();
                case 5 -> withdrawMenu();
                case 6 -> historyMenu();
                case 7 -> billMenu();
                case 8 -> accountQueueMenu();
                case 0 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addAccount() {
        int    num  = readInt("Account number: ");
        System.out.print("Username: ");
        String name = sc.nextLine().trim();
        double bal  = readDouble("Initial balance: ");
        accounts.addAccount(num, name, bal);
    }

    static void searchAccount() {
        System.out.print("Enter username: ");
        String name = sc.nextLine().trim();
        MyLinkedList.Node node = accounts.searchByUsername(name);
        if (node == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println("Found: " + node.data.username
                    + " - Balance: " + (int) node.data.balance);
        }
    }

    static void depositMenu() {
        System.out.print("Enter username: ");
        String name   = sc.nextLine().trim();
        double amount = readDouble("Amount: ");
        accounts.deposit(name, amount);
        history.push("Deposit " + (int) amount + " to " + name);
    }

    static void withdrawMenu() {
        System.out.print("Enter username: ");
        String name   = sc.nextLine().trim();
        double amount = readDouble("Amount: ");
        accounts.withdraw(name, amount);
        history.push("Withdraw " + (int) amount + " from " + name);
    }

    static void historyMenu() {
        while (true) {
            System.out.println("--- Transaction History ---");
            System.out.println("1. Show last transaction");
            System.out.println("2. Undo last transaction");
            System.out.println("0. Back");

            switch (readInt("Choice: ")) {
                case 1 -> {
                    String last = history.peek();
                    if (last == null) System.out.println("No transactions.");
                    else System.out.println("Last transaction: " + last);
                }
                case 2 -> {
                    String removed = history.pop();
                    if (removed == null) System.out.println("No transactions to undo.");
                    else System.out.println("Undo -> " + removed + " removed");
                }
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void billMenu() {
        while (true) {
            System.out.println("--- Bill Payment Queue ---");
            System.out.println("1. Add bill");
            System.out.println("2. Process next bill");
            System.out.println("3. Display queue");
            System.out.println("0. Back");

            switch (readInt("Choice: ")) {
                case 1 -> {
                    System.out.print("Bill name: ");
                    billQueue.enqueue(sc.nextLine().trim());
                }
                case 2 -> {
                    String bill = billQueue.dequeue();
                    if (bill == null) {
                        System.out.println("No bills in queue.");
                    } else {
                        System.out.println("Processing: " + bill);
                        history.push("Bill payment: " + bill);
                        billQueue.display();
                    }
                }
                case 3 -> billQueue.display();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void accountQueueMenu() {
        while (true) {
            System.out.println("--- Account Opening Queue (Admin) ---");
            System.out.println("1. Submit request");
            System.out.println("2. Process next request");
            System.out.println("3. Display pending");
            System.out.println("0. Back");

            switch (readInt("Choice: ")) {
                case 1 -> {
                    int    num  = readInt("Account number: ");
                    System.out.print("Username: ");
                    String name = sc.nextLine().trim();
                    double bal  = readDouble("Initial balance: ");
                    accountQueue.submitRequest(num, name, bal);
                }
                case 2 -> accountQueue.processNext(accounts);
                case 3 -> accountQueue.displayPending();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}