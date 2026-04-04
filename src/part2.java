public class part2 {
        static class BankAccount {
            String username;
            int number;
            double balance;

            BankAccount(String username, int number, double balance) {
                this.username = username;
                this.number = number;
                this.balance = balance;
            }

            @Override
            public String toString() {
                return String.format("Account #%s | Owner: %-15s | Balance: $%.2f",
                        number, username, balance);
            }
        }

        public static void main(String[] args) {
            BankAccount[] accounts = new BankAccount[3];

            accounts[0] = new BankAccount("Alice Johnson",  1001, 200.75);
            accounts[1] = new BankAccount("Bob Smith",      1002,   980.00);
            accounts[2] = new BankAccount("Carol Williams", 1003, 450.50);

            System.out.println("===== Bank Accounts =====");
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
            System.out.println("=========================");
        }
    }
