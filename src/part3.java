import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class part3 {

    static Scanner sc = new Scanner(System.in);

    static LinkedList<String> accounts = new LinkedList<>();
    static LinkedList<Double> balances = new LinkedList<>();

    static Stack<String> history = new Stack<>();
    static Queue<String> accountQueue = new LinkedList<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("1. Bank");
            System.out.println("2. ATM");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            int choice = sc.nextInt();

            if (choice == 1) bankMenu();
            else if (choice == 2) atmMenu();
            else if (choice == 3) adminMenu();
            else break;
        }
    }

    static void bankMenu() {
        System.out.println("1. Open Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        int c = sc.nextInt();

        if (c == 1) {
            System.out.print("Enter name: ");
            String name = sc.next();
            accountQueue.add(name);
            System.out.println("Request added!");

        } else if (c == 2) {
            int i = find();
            if (i != -1) {
                System.out.print("Amount: ");
                double a = sc.nextDouble();
                balances.set(i, balances.get(i) + a);
                history.push("Deposit " + a);
            }

        } else if (c == 3) {
            int i = find();
            if (i != -1) {
                System.out.print("Amount: ");
                double a = sc.nextDouble();
                if (balances.get(i) >= a) {
                    balances.set(i, balances.get(i) - a);
                    history.push("Withdraw " + a);
                } else {
                    System.out.println("Not enough money");
                }
            }
        }
    }

    static void atmMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Withdraw");
        int c = sc.nextInt();

        int i = find();
        if (i == -1) return;

        if (c == 1) {
            System.out.println("Balance: " + balances.get(i));
        } else if (c == 2) {
            System.out.print("Amount: ");
            double a = sc.nextDouble();
            if (balances.get(i) >= a) {
                balances.set(i, balances.get(i) - a);
                history.push("ATM Withdraw " + a);
            } else {
                System.out.println("Not enough money");
            }
        }
    }

    static void adminMenu() {
        System.out.println("1. Process Queue");
        System.out.println("2. History");
        int c = sc.nextInt();

        if (c == 1) {
            if (accountQueue.isEmpty()) {
                System.out.println("No requests");
            } else {
                String name = accountQueue.poll();
                accounts.add(name);
                balances.add(0.0);
                System.out.println("Account created: " + name);
            }

        } else if (c == 2) {
            for (String h : history) {
                System.out.println(h);
            }
        }
    }

    static int find() {
        System.out.print("Enter name: ");
        String name = sc.next();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(name)) return i;
        }

        System.out.println("Not found!");
        return -1;
    }
}
