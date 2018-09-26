package lucius.justtest.java.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.util.Arrays.stream;

/**
 * https://www.javatpoint.com/data-transfer-between-channels
 * <p>
 * 如果两个Channel中其中一个是FileChannel，那么直接可以从Channel到Channel传输数据。这种传输方式是经过优化的，效率较高。
 * FileChannel类中有以下两个方法，用于channels间的数据传输：
 * 1. FileChannel.transferTo()方法
 * 2. FileChannel.transferFrom()方法
 * Created by Lucius on 9/26/18.
 */
public class ChannelToChannelTransferExample {
    public static void main(String[] args) throws Exception {
        WritableByteChannel targetChannel = FileChannel.open(Paths.get("docs/tmp/inputJoin.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        String[] inputFiles = new String[]{"docs/tmp/input1.txt", "docs/tmp/input2.txt"};
        stream(inputFiles).forEach(file -> {
            try {
                FileChannel inputChannel = FileChannel.open(Paths.get(file));
                inputChannel.transferTo(0, inputChannel.size(), targetChannel);
                inputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        targetChannel.close();
    }
}
