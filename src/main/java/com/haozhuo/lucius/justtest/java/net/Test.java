package com.haozhuo.lucius.justtest.java.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Created by Lucius on 8/6/18.
 */
public class Test {
    public InetSocketAddress createSocketAddress() {
        String target = "http://datanode153:8080/data/work";
        InetSocketAddress inetSocketAddress = null;
        try {
            boolean hasScheme = target.contains("://");
            //必须要有带有://及之前的内容。如：http://  如果没有就赋予一个：dummyscheme://
            URI uri = hasScheme ? URI.create(target) : URI.create("dummyscheme://"+target);

            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();

            System.out.println("host: "+host); //host: datanode153
            System.out.println("port: "+port); //port: 8080
            System.out.println("path: " + path); //path: /data/work

            InetAddress inetAddress = InetAddress.getByName(host);
            System.out.println("inetAddress: " + inetAddress); //inetAddress: datanode153/192.168.1.153

            inetSocketAddress = new InetSocketAddress(inetAddress, port);

            System.out.println("inetSocketAddress: " + inetSocketAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inetSocketAddress;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.createSocketAddress();
    }
}
