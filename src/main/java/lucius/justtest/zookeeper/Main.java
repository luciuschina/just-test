package lucius.justtest.zookeeper;


/**
 * Created by Lucius on 9/13/18.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        startNode("001");
        startNode("002");
        Thread.sleep(600000);

    }

    public static void startNode(String serverId) throws Exception {
        /*
        1. 192.168.1.150:2181,192.168.1.152:2181 (一般情况)
        2. 192.168.1.150:2181,192.168.1.152:2181/lucius (以/lucius这个znode为根目录)
        3. 192.168.1.150,192.168.1.152 (省略端口的情况下，默认是2181)
        4. namenode:2181,datanode152:2181
         */
        Master client = new Master("namenode:2181,datanode152:2181/lucius", serverId);
        //我们连接到ZooKeeper后，后台就会有⼀个守护线程来维护这个ZooKeeper会话
        client.startZkClient();
        client.runForMaster();
    }
}
