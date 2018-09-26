package lucius.justtest.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 解决读取中文乱码的问题
 * Created by Lucius on 9/25/18.
 */
public class DecoderExample {
    public static void main(String[] args) throws IOException {

        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        Path path = Paths.get("docs/tmp", "read.txt");
        FileChannel fileChannel = FileChannel.open(path);
        //上面那句效果好像跟这句一样：FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        CharBuffer charBuffer = CharBuffer.allocate(512);

        while (fileChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            decoder.decode(byteBuffer, charBuffer, false);
            charBuffer.flip();
            while (charBuffer.hasRemaining()) {
                System.out.print(charBuffer.get());
            }
            byteBuffer.clear();
            charBuffer.clear();
        }
    }
}
