import java.util.LinkedList;
import java.util.Queue;

class AccountQueue {
    Queue<BankAccount> accountRequests = new LinkedList<>();

    void addRequest(int number, String username, double balance) {
        accountRequests.add(new BankAccount(number, username, balance));
        System.out.println("Request submitted for: " + username);
    }

    void processNext(LinkedList<BankAccount> accounts) {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        BankAccount approved = accountRequests.poll();
        accounts.add(approved);
        System.out.println("Approved and added: " + approved);
    }

    void displayPending() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        System.out.println("Pending Requests:");
        int i = 1;
        for (BankAccount a : accountRequests) System.out.println(i++ + ". " + a);
    }
}