package lucius.justtest.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;


/**
 * Created by Lucius on 9/13/18.
 */
public class Master implements Watcher {
    private ZooKeeper zk;
    private String hostPort;
    private String serverId;
    private boolean isLeader = false;
    public final static String masterZnode = "/my_master";

    /**
     * 会启动多个进程，每个进程传入的serverId必须不同
     *
     * @param hostPort
     * @param serverId
     */
    public Master(String hostPort, String serverId) {
        this.serverId = serverId;
        this.hostPort = hostPort;
    }

    /**
     * 启动zookeeper客户端
     */
    public void startZkClient() {
       /*
        参数1. connectString: 包含主机名和ZooKeeper服务器的端口
        参数2. sessionTimeout: 以毫秒为单位，表⽰ZooKeeper等待客户端通信的最长时间，之后会声明会话已死亡。
           ⽬前我们使⽤15000，即15秒。这就是说如果ZooKeeper与客户端有15秒的时间无法进行通信，ZooKeeper就会终止客户端的会话。
        参数3. 用于接收会话事件的一个对象，这个对象需要我们自己创建。因为Watcher定义为接口，所以我们需要自己实现这个类，然后
           初始化这个类的实例并传入ZooKeeper的构造函数中。客户端使用Watcher接口来监控与ZooKeeper之间会话的健康情况。
           与ZooKeeper服务器之间建立或失去连接时就会产生事件。它们同样还能用于监控ZooKeeper数据的变化。最终，如果与
           ZooKeeper的会话过期，也会通过Watcher接口传递事件来通知客户端的应用。
         */
        try {
            this.zk = new ZooKeeper(hostPort, 15000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭zookeeper客户端
     * 当程序结束时，最好的⽅式是使会话⽴即消失。这可以通过ZooKeeper.close()⽅法来结束。
     * ⼀旦调⽤close⽅法后，ZooKeeper对象实例所表⽰的会话就会被销毁.
     * 如果不调用ZooKeeper.close()⽅法，需要等到超时时间过了之后，会话才会销毁，
     */
    public void stopZkClient() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现Watcher接口中的抽象方法
     *
     * @param event
     */
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    private boolean checkMaster() {

        while (true) {
            try {
                Stat stat = new Stat();
                zk.getData(masterZnode, false, stat);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void runForMaster() throws InterruptedException {
        while (true) {
            try {
            /*
            创建一个/my_master的临时znode,数据内容是serverId
            四个参数的含义：
            1.尝试创建znode节点/my_master。如果这个znode节点存在，create就会失败。
            2.在/my_master节点的数据字段保存服务器的唯⼀ID。
            3.使用开放的ACL策略。
            4.创建的节点类型为EPHEMERAL(临时节点)。
             */
                zk.create(masterZnode, serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                //如果其他节点已经创建了/my_master，那么上面这个步骤会报出：NodeExistsException: KeeperErrorCode = NodeExists for /my_master
                isLeader = true;
                System.out.println("server:" + serverId + "成为leader！！");
                break;
            } catch (KeeperException.NodeExistsException e) {
                isLeader = true;
                System.out.println("已经存在leader");
                break;
            } catch (KeeperException e) {

            }
        }

    }
}
