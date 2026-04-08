class part2 {

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

    public static void main(String[] args) {

        BankAccount[] accounts = new BankAccount[3];

        accounts[0] = new BankAccount(1, "Ali", 1000);
        accounts[1] = new BankAccount(2, "Dana", 2000);
        accounts[2] = new BankAccount(3, "Omar", 3000);

        for (int i = 0; i < accounts.length; i++) {
            System.out.println(
                    accounts[i].id + " " +
                            accounts[i].name + " " +
                            accounts[i].balance
            );
        }
    }
}
