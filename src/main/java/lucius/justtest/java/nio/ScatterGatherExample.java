package lucius.justtest.java.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;


/**
 * https://www.javatpoint.com/scatter-gather-or-vectored-input-output
 *
 * “scattering read”用于从单个channel中读取数据到多个buffers中。
 * “gathering write”用于将数据从多个buffers中写入到单个channel中。
 */
public class ScatterGatherExample {
    public static void main(String[] args) throws IOException {
        String data = "Scattering and Gathering examples!";
        String file = "docs/tmp/write.txt";
        gatherBytes(file, data);
        scatterBytes(file);
    }

    //scatterBytes() is used for reading the bytes from a file channel into a set of buffers.
    public static void scatterBytes(String file) {
        //The First Buffer is used for holding a random number
        ByteBuffer buffer1 = ByteBuffer.allocate(8);
        //The Second Buffer is used for holding a data that we want to write
        ByteBuffer buffer2 = ByteBuffer.allocate(400);
        ScatteringByteChannel scatter = createChannelInstance(file, false);
        //Reading a data from the channel
        try {
            scatter.read(new ByteBuffer[]{buffer1, buffer2});
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Read the two buffers seperately
        buffer1.rewind();
        buffer2.rewind();

        int bufferOne = buffer1.asIntBuffer().get();
        String bufferTwo = buffer2.asCharBuffer().toString();
        //Verification of content
        System.out.println(bufferOne);
        System.out.println(bufferTwo);
    }

    //gatherBytes() is used for reading the bytes from the buffers and write it to a file channel
    public static void gatherBytes(String file, String data) throws IOException {
        //The First Buffer is used for holding a random number
        ByteBuffer buffer1 = ByteBuffer.allocate(8);
        //The Second Buffer is used for holding a data that we want to write
        ByteBuffer buffer2 = ByteBuffer.allocate(400);
        buffer1.asIntBuffer().put(420);
        buffer2.asCharBuffer().put(data);
        GatheringByteChannel gatherer = createChannelInstance(file, true);

        //write the data into file
        try {
            gatherer.write(new ByteBuffer[]{buffer1, buffer2});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static FileChannel createChannelInstance(String file, boolean isOutput) {
        FileChannel fileChannel = null;
        try {
            if (isOutput) {
                fileChannel = new FileOutputStream(file).getChannel();
                //fileChannel = FileChannel.open(Paths.get(file), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            } else {
                fileChannel = new FileInputStream(file).getChannel();
                //fileChannel = FileChannel.open(Paths.get(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileChannel;
    }
}
