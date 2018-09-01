package lucius.justtest.java.concurrency.chapter3.phaser.exp1;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lucius on 8/31/18.
 */
public class FileSearch implements Runnable {
    private String initPath;
    //存储要查找文件的扩展名
    private String end;

    //store the full paths of the files which has found
    private List<String> results;

    private Phaser phaser;

    public FileSearch(String initPath, String end,Phaser phaser ) {
        this.phaser = phaser;
        this.end = end;
        this.initPath = initPath;
        results = new ArrayList<String>();
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }

    private void directoryProcess(File file) {
        File list[] = file.listFiles();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    directoryProcess(list[i]);
                } else {
                    fileProcess(list[i]);
                }
            }
        }
    }

    private void filterResults() {
        List<String> newResults = new ArrayList<String>();
        long actualDate = new Date().getTime();
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(results.get(i));
            }
        }
        results = newResults;
    }

    /**
     * 检查结果列表的长度，如果是0，将没有找到任何文件的信息打印到控制台，并且调用Phaser对象的arriveAndDeregister()方法，
     * 来通知Phaser对象当前线程已经结束这个阶段，并且将不再参与接下来阶段的操作。
     *
     * @return
     */
    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo() {
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }

    public void run() {
        //首先，调用Phaser对象的arriveAndAwaitAdvance()方法，使查找工作在所有线程都被创建之后再开始
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: ========阶段1开始===========.\n", Thread.currentThread().getName());
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }
        //使用checkResults()方法检查结果集是否为空，如果是，结束对应线程，并使用关键字return返回
        if (!checkResults()) {
            return;
        }

        System.out.printf("%s: ========阶段2开始===========.\n", Thread.currentThread().getName());
        //过滤结果集
        filterResults();
        //使用checkResults()方法再次检查新的结果集是否为空，如果是，对应线程将结束，并使用return返回
        if (!checkResults()) {
            return;
        }

        System.out.printf("%s: ========阶段3开始===========.\n", Thread.currentThread().getName());
        showInfo();
        System.out.printf("%s: ========start call arriveAndDeregister===========.\n", Thread.currentThread().getName());
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed\n", Thread.currentThread().getName());

    }
}

































