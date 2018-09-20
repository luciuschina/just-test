package lucius.justtest.java.concurrency.chapter6.exp8;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Lucius on 9/7/18.
 */
public class Account {
    private AtomicLong balance;

    public Account() {
        this.balance = new AtomicLong();
    }

    public long getBalance() {
        return balance.get();
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
    }
    public void addAmount(long amount) {
        this.balance.addAndGet(amount);
    }
    public void subtractAmount(long amount){
        this.balance.getAndAdd(-amount);
    }

}
