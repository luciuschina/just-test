package lucius.justtest.java.concurrency.chapter3.phaser.exp1;

import java.util.concurrent.Phaser;

/**
 * Created by Lucius on 8/31/18.
 */
public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch search1 = new FileSearch("/data/work/luciuschina/just-test", "java", phaser);
        FileSearch search2 = new FileSearch("/data/work/luciuschina/spring-apps", "java", phaser);
        FileSearch search3 = new FileSearch("/data/work/luciuschina/hadoop-trunk", "java", phaser);
        Thread search1Thread = new Thread(search1, "Search1");
        search1Thread.start();
        Thread search2Thread = new Thread(search2, "Search2");
        search2Thread.start();
        Thread search3Thread = new Thread(search3, "Search3");
        search3Thread.start();
        try {
            search1Thread.join();
            search2Thread.join();
            search3Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminated: " + phaser.isTerminated());
    }
}
