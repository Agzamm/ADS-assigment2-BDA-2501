import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BillQueue {
    Queue<String> billQueue = new LinkedList<>();
    Scanner sc = new Scanner(System.in);

    void addBill() {
        System.out.print("Enter bill name: ");
        String bill = sc.nextLine().trim();
        System.out.print("Enter amount: ");
        double amount = readDouble();
        String entry = bill + " - " + String.format("%.2f", amount);
        billQueue.add(entry);
        System.out.println("Bill added to queue: " + entry);
    }

    void processNextBill() {
        if (billQueue.isEmpty()) {
            System.out.println("No bills in queue");
        } else {
            System.out.println("Processing bill: " + billQueue.poll());
            System.out.println("Bill processed successfully!");
        }
    }

    void displayQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty");
            return;
        }
        System.out.println("Current Bill Queue:");
        int i = 1;
        for (String bill : billQueue) {
            System.out.println(i++ + ". " + bill);
        }
    }

    private double readDouble() {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input");
            }
        }
    }

    void menu() {
        int choice;
        while (true) {
            System.out.println("--- Bill Payment Queue ---");
            System.out.println("1. Add Bill Payment Request");
            System.out.println("2. Process Next Bill");
            System.out.println("3. Display Queue");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            String line = sc.nextLine().trim();
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
            }

            switch (choice) {
                case 1:
                    addBill();
                    break;
                case 2:
                    processNextBill();
                    break;
                case 3:
                    displayQueue();
                    break;
                case 0: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        new BillQueue().menu();
    }
}