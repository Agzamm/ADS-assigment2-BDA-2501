import java.util.Scanner;

class BankAccount {
    int accountNumber;
    String username;
    int balance;

    BankAccount(int accountNumber, String username, int balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

class MyLinkedList {

    static class Node {
        BankAccount data;
        Node next;

        Node(BankAccount data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void addAccount(int accountNumber, String username, int balance) {
        Node newNode = new Node(new BankAccount(accountNumber, username, balance));

        if (head == null) {
            head = newNode;
        }
        else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void displayAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data.username + " - Balance: " + current.data.balance);
            current = current.next;
        }
    }

    public Node searchByUsername(String username) {
        Node current = head;
        while (current != null) {
            if (current.data.username.equalsIgnoreCase(username)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void deposit(String username, int amount) {
        Node node = searchByUsername(username);
        if (node != null) {
            node.data.balance += amount;
        }
    }

    public void withdraw(String username, int amount) {
        Node node = searchByUsername(username);
        if (node != null && node.data.balance >= amount) {
            node.data.balance -= amount;
        }
    }
}

class MyStack {
    private int[] arr;
    private int capacity;
    private int top;

    public MyStack(int cap) {
        capacity = cap;
        arr = new int[capacity];
        top = -1;
    }

    public void push(int x) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }

    public int peek() {
        if (top == -1) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

class MyQueue {

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public MyQueue() {
        front = rear = null;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int dequeue() {
        if (front == null) {
            System.out.println("Queue Underflow");
            return -1;
        }

        int value = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return value;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

public class Bank {

    static Scanner sc = new Scanner(System.in);
    static MyLinkedList accounts = new MyLinkedList();
    static MyStack history = new MyStack(100);
    static MyQueue queue = new MyQueue();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Account");
            System.out.println("2. Display Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Push to Stack");
            System.out.println("6. Pop from Stack");
            System.out.println("7. Enqueue");
            System.out.println("8. Dequeue");
            System.out.println("0. Exit");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String name = sc.nextLine();
                    System.out.print("Balance: ");
                    int bal = Integer.parseInt(sc.nextLine());
                    accounts.addAccount(0, name, bal);
                    break;

                case 2:
                    accounts.displayAll();
                    break;

                case 3:
                    System.out.print("Username: ");
                    name = sc.nextLine();
                    System.out.print("Amount: ");
                    int dep = Integer.parseInt(sc.nextLine());
                    accounts.deposit(name, dep);
                    break;

                case 4:
                    System.out.print("Username: ");
                    name = sc.nextLine();
                    System.out.print("Amount: ");
                    int wit = Integer.parseInt(sc.nextLine());
                    accounts.withdraw(name, wit);
                    break;

                case 5:
                    System.out.print("Value: ");
                    history.push(Integer.parseInt(sc.nextLine()));
                    break;

                case 6:
                    System.out.println("Popped: " + history.pop());
                    break;

                case 7:
                    System.out.print("Value: ");
                    queue.enqueue(Integer.parseInt(sc.nextLine()));
                    break;

                case 8:
                    System.out.println("Dequeued: " + queue.dequeue());
                    break;

                case 0:
                    return;
            }
        }
    }
}