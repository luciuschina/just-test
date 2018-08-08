package lucius.justtest.java.concurrency.chapter2.synchronize.exp2;

/**
 * Created by Lucius on 8/8/18.
 * 实现售票处类
 */
public class TicketOffice2 implements Runnable {
    private Cinema cinema;

    public TicketOffice2(Cinema cinema) {
        this.cinema = cinema;
    }

    public void run() {

            cinema.sellTicket2(5);
            cinema.sellTicket1(2);
            cinema.returnTicket2(2);
            cinema.sellTicket1(1);
            cinema.returnTicket2(2);
            cinema.sellTicket1(3);
            cinema.returnTicket2(1);
            cinema.sellTicket2(2);
            cinema.returnTicket2(2);
            cinema.sellTicket1(2);


    }
}
