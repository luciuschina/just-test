package lucius.justtest.java.concurrency.chapter6.exp8;

/**
 * Created by Lucius on 9/7/18.
 */
public class Company implements Runnable {
    private Account account;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.addAmount(1000);
        }
    }

    public Company(Account account) {

        this.account = account;
    }
}
