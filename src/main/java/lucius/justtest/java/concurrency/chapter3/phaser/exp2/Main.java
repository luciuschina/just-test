package lucius.justtest.java.concurrency.chapter3.phaser.exp2;

/**
 * Created by Lucius on 8/31/18.
 */
public class Main {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();
        Student students[] = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(phaser);
            phaser.register();
        }
        Thread threads[] = new Thread[students.length];
        for (int i = 0; i < students.length; i++) {
            threads[i] = new Thread(students[i],"Student_"+i);
            threads[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
