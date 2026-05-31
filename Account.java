package Transaction.system;

public class Account {

    int accNo;
    String name;
    double balance;

    // Deposit
    synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(name + " deposited: " + amount);
    }

    // Withdraw
    synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(name + " withdrew: " + amount);
        } else {
            System.out.println(name + " - Insufficient Balance");
        }
    }

    // Transfer
    synchronized void transfer(Account receiver, double amount) {
        if (balance >= amount) {
            balance -= amount;
            receiver.balance += amount;
            System.out.println(name + " transferred " + amount + " to " + receiver.name);
        } else {
            System.out.println(name + " - Transfer Failed (Insufficient Balance)");
        }
    }

    // Show Balance
    void showBalance() {
        System.out.println(name + " Balance: " + balance);
    }
}