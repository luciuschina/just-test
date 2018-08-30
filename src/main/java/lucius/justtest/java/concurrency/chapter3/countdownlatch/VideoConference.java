package lucius.justtest.java.concurrency.chapter3.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Lucius on 8/30/18.
 */
public class VideoConference implements Runnable {
    private final CountDownLatch controller;

    public VideoConference(int number) {
        this.controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.", name);
        controller.countDown(); //每次调用计数减1
        System.out.printf("VideoConference: Waiting for %d participants. \n", controller.getCount());
    }

    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants. \n", controller.getCount());
        try {
            controller.await();//当计数变为0时，唤醒开始执行下面语句
            System.out.printf("VideoConference: All the participants have come\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
