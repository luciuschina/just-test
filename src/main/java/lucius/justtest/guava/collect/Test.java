package lucius.justtest.guava.collect;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Lucius on 8/7/18.
 */
public class Test {
    public static void testSetsDifference() {
        Set<String> oldAddrs = Sets.newHashSet("192.168.1.10","192.168.1.11");

        Set<String> newAddrs = Sets.newHashSet("192.168.1.12","192.168.1.11");
        Set<String> addedAddrs = Sets.difference(newAddrs, oldAddrs);
        for(String addr: addedAddrs) {
            System.out.println(addr);
        }
    }

    public static void main(String[] args) {
        testSetsDifference();
    }
}
