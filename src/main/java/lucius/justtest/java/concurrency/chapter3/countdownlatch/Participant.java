package lucius.justtest.java.concurrency.chapter3.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 8/30/18.
 */
public class Participant implements Runnable {
    private VideoConference conference;
    private String name;

    public Participant(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    public void run() {
        long duration = (long)(Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
}
