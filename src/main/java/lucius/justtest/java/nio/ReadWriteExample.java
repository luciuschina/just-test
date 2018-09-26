package lucius.justtest.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * https://javapapers.com/java/java-nio-buffer/
 *
 * Created by Lucius on 9/25/18.
 */
public class ReadWriteExample {
    public static void main(String[] args) throws IOException{
        Path path = Paths.get("docs/tmp/write.txt");
        write(path);
        read(path);
    }
    private static void write(Path path) throws IOException {
        String input = "NIO Buffer Hello World!\nNetty";
        byte[] inputBytes = input.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(inputBytes);
        FileChannel channelWrite = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        channelWrite.write(byteBuffer);
        channelWrite.close();
    }

    private static void read(Path path) throws IOException {
        FileChannel channelRead = FileChannel.open(path);
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        channelRead.read(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        String fileContent = new String(byteArray).trim();
        System.out.println("File Content: " + fileContent);
        channelRead.close();
    }
}
