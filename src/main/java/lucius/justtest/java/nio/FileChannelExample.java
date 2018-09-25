package lucius.justtest.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://javapapers.com/java/java-nio-channel/
 *
 * Following example reads from a text file and prints the content to the console.
 * We have used RandomAccessFile and FileChannel to read the file into a ByteBuffer.
 * We read 512 bytes of data into buffer then invoke flip method to make it ready for
 * the get operation. On get we print the content to console the clear the buffer to
 * keep it ready for the next sequence of read. This keeps on continuing until the end of file.
 */
public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("docs/tmp/read.txt", "r");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        // fileChannel.read(byteBuffer): Reads a sequence of bytes from this channel into the given buffer
        while (fileChannel.read(byteBuffer) > 0) {
            // flip the buffer to prepare for get operation
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                // byteBuffer.get(): Reads the byte at this buffer's current position, and then increments the position.
                System.out.print((char) byteBuffer.get());
            }
            // clear the buffer ready for next sequence of read
            byteBuffer.clear();
        }
        file.close();
    }
}