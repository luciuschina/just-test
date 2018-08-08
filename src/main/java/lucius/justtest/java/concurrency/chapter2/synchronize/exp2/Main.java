package lucius.justtest.java.concurrency.chapter2.synchronize.exp2;

/**
 * Created by Lucius on 8/8/18.
 */
public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        int num = 10;
        Thread[] threadsTicketOffice1 = new Thread[num];
        for (int i = 0; i < num; i++) {
            threadsTicketOffice1[i] =  new Thread(new TicketOffice1(cinema), "TicketOffice1_"+i);
        }

        Thread[] threadsTicketOffice2 = new Thread[num];
        for (int i = 0; i < num; i++) {
            threadsTicketOffice2[i] =  new Thread(new TicketOffice2(cinema), "TicketOffice2_"+i);
        }

        for (int i = 0; i < num; i++) {
            threadsTicketOffice1[i].start();
            threadsTicketOffice2[i].start();
        }

        try {
            for (int i = 0; i < num; i++) {
                threadsTicketOffice1[i].join();
                threadsTicketOffice2[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());
    }
}
