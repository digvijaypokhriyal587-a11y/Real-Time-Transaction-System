package Transaction.system;

public class TransactionThread extends Thread {

    Account sender;
    Account receiver;

    public TransactionThread(Account sender, Account receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void run() {
        sender.transfer(receiver, 100);
    }
}
