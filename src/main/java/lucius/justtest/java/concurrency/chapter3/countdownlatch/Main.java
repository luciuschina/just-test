package lucius.justtest.java.concurrency.chapter3.countdownlatch;

/**
 * Created by Lucius on 8/30/18.
 */
public class Main {
    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);
        Thread threadConference = new Thread(conference);
        threadConference.start();
        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "participant_"+i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
