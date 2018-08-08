package lucius.justtest.java.concurrency.chapter2.synchronize.exp1;

/**
 * 尝试去掉Account类的synchronized后再执行
 */
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);
        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);
        //将初始余额打印到控制台，并启动这2个线程
        System.out.printf("Account: Initial Balance: %f\n", account.getBalance());

        companyThread.start();
        bankThread.start();

        //等待companyThread和bankThread执行完成后，打印出余额
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account: Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
