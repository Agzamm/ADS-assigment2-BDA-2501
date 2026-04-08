import java.util.Scanner;

class part3 {

    static class BankAccount {
        int id;
        String name;
        double balance;

        BankAccount(int id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = balance;
        }
    }

    static class AccountList {
        class Node {
            BankAccount acc;
            Node next;

            Node(BankAccount acc) {
                this.acc = acc;
            }
        }

        Node head;

        void add(BankAccount acc) {
            Node n = new Node(acc);
            if (head == null) head = n;
            else {
                Node t = head;
                while (t.next != null) t = t.next;
                t.next = n;
            }
        }

        BankAccount find(int id) {
            Node t = head;
            while (t != null) {
                if (t.acc.id == id) return t.acc;
                t = t.next;
            }
            return null;
        }
    }

    static class myStack {
        String[] arr;
        int top = -1;

        myStack(int size) {
            arr = new String[size];
        }

        void push(String x) {
            if (top < arr.length - 1)
                arr[++top] = x;
        }

        void print() {
            for (int i = top; i >= 0; i--)
                System.out.println(arr[i]);
        }
    }

    static class myQueue {
        class Node {
            String data;
            Node next;

            Node(String data) {
                this.data = data;
            }
        }

        Node front, rear;

        void enqueue(String x) {
            Node n = new Node(x);
            if (rear == null) front = rear = n;
            else {
                rear.next = n;
                rear = n;
            }
        }

        void dequeue() {
            if (front != null) {
                System.out.println("Processed: " + front.data);
                front = front.next;
            }
        }

        void print() {
            Node t = front;
            while (t != null) {
                System.out.println(t.data);
                t = t.next;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AccountList list = new AccountList();
        myStack history = new myStack(20);
        myQueue queue = new myQueue();

        list.add(new BankAccount(1, "Ali", 1000));
        list.add(new BankAccount(2, "Dana", 2000));
        list.add(new BankAccount(3, "Omar", 3000));

        int choice;

        do {
            System.out.println("\n1-Bank 2-ATM 3-Admin 4-Exit");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("1-Request 2-Deposit 3-Withdraw");
                int b = sc.nextInt();

                if (b == 1) {
                    System.out.print("Name: ");
                    String name = sc.next();
                    queue.enqueue(name);
                }

                if (b == 2) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    BankAccount acc = list.find(id);
                    if (acc != null) {
                        System.out.print("Amount: ");
                        double a = sc.nextDouble();
                        acc.balance += a;
                        history.push("Deposit " + a);
                    }
                }

                if (b == 3) {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    BankAccount acc = list.find(id);
                    if (acc != null) {
                        System.out.print("Amount: ");
                        double a = sc.nextDouble();
                        acc.balance -= a;
                        history.push("Withdraw " + a);
                    }
                }
            }

            if (choice == 2) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                BankAccount acc = list.find(id);
                if (acc != null) {
                    System.out.println("Balance: " + acc.balance);
                }
            }

            if (choice == 3) {
                System.out.println("Requests:");
                queue.print();
                queue.dequeue();

                System.out.println("History:");
                history.print();
            }

        } while (choice != 4);

        sc.close();
    }
}
