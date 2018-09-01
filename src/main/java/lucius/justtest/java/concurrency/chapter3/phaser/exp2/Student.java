package lucius.justtest.java.concurrency.chapter3.phaser.exp2;

import lucius.justtest.java.concurrency.chapter1.ThreadCreaterApp;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 8/31/18.
 */
public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    private void doExercise1() {
        try {
            long duration= (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercise2() {
        try {
            long duration= (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercise3() {
        try {
            long duration= (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        System.out.printf("%s: Has arrived to do the exam. %s.  phase: %d\n", Thread.currentThread().getName(), new Date(), phaser.getPhase());

        phaser.arriveAndAwaitAdvance();

        //phaser.onAdvance(0, int registeredParties)

        System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise1();
        System.out.printf("%s: Has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        //phaser.onAdvance(1, int registeredParties)

        System.out.printf("%s: Is going to do the second exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise2();
        System.out.printf("%s: Has done the secnod exercise. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        //phaser.onAdvance(2, int registeredParties)

        System.out.printf("%s: Is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise2();
        System.out.printf("%s: Has finish the exam. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        //phaser.onAdvance(3, int registeredParties)

    }
}
