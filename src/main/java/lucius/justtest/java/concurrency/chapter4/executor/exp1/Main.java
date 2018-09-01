package lucius.justtest.java.concurrency.chapter4.executor.exp1;

/**
 * Created by Lucius on 9/1/18.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask("Task_"+i);
            server.executeTask(task);
        }
        System.out.println("所有tasks都已经提交！！");
        server.endServer();
    }
}
