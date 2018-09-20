package lucius.justtest.zookeeper;


/**
 * Created by Lucius on 9/13/18.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        startNode("001");
        startNode("002");
        Thread.sleep(600000);
//        client.stopZkClient();
    }

    public static void startNode(String serverId) {
        Master client = new Master("192.168.1.150:2181",serverId);
        //我们连接到ZooKeeper后，后台就会有⼀个守护线程来维护这个ZooKeeper会话
        client.startZkClient();
        client.runForMaster();
    }
}
