package lucius.justtest.java.concurrency.chapter2.synchronize.exp2;

/**
 * Created by Lucius on 8/8/18.
 * 实现售票处类
 */
public class TicketOffice1 implements Runnable {
    private Cinema cinema;

    public TicketOffice1(Cinema cinema) {
        this.cinema = cinema;
    }

    public void run() {
            cinema.sellTicket1(3);
            cinema.returnTicket1(1);
            cinema.sellTicket1(2);
            cinema.returnTicket1(1);
            cinema.sellTicket2(2);
            cinema.returnTicket1(3);
            cinema.sellTicket1(2);
            cinema.sellTicket2(2);


    }
}
