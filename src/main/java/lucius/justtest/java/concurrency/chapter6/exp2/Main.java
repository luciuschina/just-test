package lucius.justtest.java.concurrency.chapter6.exp2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 9/7/18.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
        Client client = new Client(list);
        Thread thread = new Thread(client);
        thread.start();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String request = list.take();
                System.out.printf("DemosFromMyProjects: Request: %s at %s. Size:%d\n",request,new Date(),list.size());
            }
        }
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.printf("DemosFromMyProjects: End of the program.\n");
    }
}
