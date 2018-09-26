# Java NIO Tutorial
参考资料：
https://javapapers.com/java/java-nio-tutorial/
https://howtodoinjava.com/java-nio-tutorials/
https://www.javatpoint.com/java-nio

#### 标准IO和NIO之间的不同：
首先，标准IO是基于流的，NIO是面向buffer的。面向Buffer的操作处理数据比较灵活，数据首先读取到缓存中，然后进行处理，所以Buffer中的数据可以反复被读取，而基于流的IO操作就不能这样操作。

其次，标准IO是阻塞IO，NIO是非阻塞IO。在标准IO中，线程会阻塞直到完成IO操作。而在NIO中允许非阻塞操作。如果IO操作现在不能获取数据，那么线程可以做一些其他的事情而不需要处于阻塞模式。使用Selector和Channel，一个线程可以管理多个Channel，可以并行化进行IO操作。Channel是Java NIO提供用于访问本地I/O机制的网关。我们需要使用Buffer与Channel进行交互，所以Channel就像两个实体进行I/O的桥梁。

## Java NIO Channel
在Java NIO中，channel用于I/O传输。Channel就像一个管道，将数据从缓存和另一端的实体中进行传输。Channel从实体中读取数据，将它放到Buffer块中，用于消费。类似地，我们需要将数据写入到Buffer块中，然后通过Channel将数据传输到另一端。Buffer是提供给Channel发送和接收数据的终端。

#### Channel性质
* 不像streams，Channel是双向的，一个Channel可用于读和写。
* Channel可进行异步读写操作。
* Channel可以处于阻塞或非阻塞模式。
* 非阻塞Channel不会将线程置于睡眠模式。
* 如果两个Channel中其中一个是FileChannel，那么直接可以从Channel到Channel传输数据。

#### Java NIO Channel 类
以下是Java NIO package中提供的主要的两个类型的Channel 类实现：
* FileChannel：用于文件读/写的channel，只能处于阻塞模式。
* SocketChannel：SocketChannel, ServerSocketChannel 和 DatagramChannel，可在非阻塞的模式下操作。

#### 例子
```java
public class FileChannelExample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("docs/java/nio/JavaNIOTutorial.md", "r");
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
```

## Java NIO Buffer
Buffer是一块将要写入到Channel中或者刚从Channel中读取到的数据。它是一个持有数据的对象，就像是NIO Channel的一个终端。Buffer提供了一种访问数据和跟踪读和写过程的正式的机制。

Buffer是old Java I/O和NIO的主要区别之一。前一种方式，数据直接从stream中读取或者直接写入stream。而NIO中数据从Buffer中读取或者写入Buffer。NIO中的Channel与标准IO中的stream类似。

#### NIO Buffer 性质
* Buffer是Java NIO的核心之一。
* Buffer提供了固定大小的容器，用于读写数据。
* 每个Buffer都是可读的，但是只有选中的buffer(chosen buffers)是可写的。
* Buffer是Channel的终端。
* 在一个只读的Buffer中，内容是不可变的。但它的mark,position和limit是可变的。
* 默认情况下，Buffer不是线程安全的。

![image](https://javapapers.com/wp-content/uploads/2015/08/JavaNIOBuffer.png)

###### Buffer Type
每个原始类型都有对应的buffer类型。所有的buffer类实现了Buffer接口。最常用的buffer类型是ByteBuffer。以下是Java NIO包中可用的buffer类型。
* ByteBuffer
* CharBuffer
* ShortBuffer
* IntBuffer
* LongBuffer
* FloatBuffer
* DoubleBuffer
* MappedByteBuffer

###### Buffer Capacity
buffer的大小是固定的。最大的固定大小称为buffer的容量(capacity)。一旦buffer满了，就必须先clear之后再写入。一旦capacity被设置了，就不能再改变了。

###### Buffer Limit
在写模式中，buffer的limit就等于capacity。
在读模式中，Buffer的limit表示，有多少数据可以读取。当从写模式切换成读模式时，写模式的position值就会赋予limit，表示写了多少数据就可以读取多少数据。

###### Buffer Position
当你将数据写入到Buffer时，会有一个position。初始position是0，当写入数据时，position会提前指向buffer中的下一个cell，position最大可以是capacity-1。
当你从Buffer读取数据时，也会有一个position。Buffer从写模式切换到(flip)读模式时，position会被重置为0。随着数据的读取，position的位置会发生变化，position提前指向下一个要读的位置。

###### Buffer mark()
Mark就像是为buffer的position设置了一个书签。当mark()方法被调用时，就会记录下当前的position。当reset()被调用，buffer的position就会变为之前mark过的position。

###### Buffer rewind()
position被设为0，mark被丢弃(设为-1)。准备重读。

###### Buffer clear()
position被设为0，mark被丢弃(设为-1), limit被设为capacity。读完准备写。

###### Buffer flip()
position被设为0，mark被丢弃(设为-1), limit被设为position。写完准备读。

#### 如何从NIO Buffer读取数据
1. 创建一个buffer，并设置capacity
```java
ByteBuffer byteBuffer = ByteBuffer.allocate(512);
```
2. 对buffer进行flip(),准备读
```java
byteBuffer.flip();
```
3. 从channel中将数据读到buffer中
```java
int numberOfBytes = fileChannel.read(byteBuffer);
```
4. 从buffer中读取数据
```java
char c = (char)byteBuffer.get();
```

#### 如何将数据写入NIO Buffer
1. 创建一个buffer，并设置capacity
```java
ByteBuffer byteBuffer = ByteBuffer.allocate(512);
```
2. 将数据放入Buffer中
```java
byteBuffer.put((byte) 0xff);
```

上面两个例子只是从Buffer中进行读写的简单例子。实际上有各种Buffer可用，各种各样的读/写方法。需要根据自己需求来选择。

#### NIO Buffer Read Write例子
```java
public class BufferExample {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("temp.txt");
		write(path);
		read(path);
	}

	private static void write(Path path) throws IOException {
		String input = "NIO Buffer Hello World!";
		byte[] inputBytes = input.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.wrap(inputBytes);
		FileChannel channelWrite = FileChannel.open(path,
				StandardOpenOption.CREATE, StandardOpenOption.WRITE);
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
```

## Java NIO Path
本节我们将看一下Java NIO中的Path和File。Path是文件IO处理的第一步。File存储在磁盘或者文件系统上。在当今的操作系统中基于树形结构的文件系统很流行。树形结构起始于一个root节点以及分支。Windows操作系统中有多个root节点(C:,D:)。
path可以唯一确定文件系统中的一个文件。有两种类型的paths：相对路径和绝对路径。

#### java.nio.file.Path
java.nio.file.Path是一个interface，可用于确定文件系统中的文件。

###### 如何创建一个file path？
以下例子是实例化一个file (relative) path, "lib"是相对于当前目录的一个目录，"nio.jar"该目录下面的文件名称。
以下四句相等。Paths.get(...)的内部实现就是FileSystems.getDefault().getPath(...)
```java
import java.nio.file.FileSystems;
import java.nio.file.Path;

Path path1 = FileSystems.getDefault().getPath("lib", "nio.jar");
Path path2 = FileSystems.getDefault().getPath("lib/nio.jar");
Path path3 = Paths.get("lib/nio.jar");
Path path4 = Paths.get("lib","nio.jar");
```

###### 获取当前path
以下代码返回当前路径的绝对路径
```java
Path currnetDirectory = Paths.get("").toAbsolutePath();
```

###### 拼接路径
```java
Path path5 = Paths.get("docs/tmp");
//输出：docs/tmp/Test.java
System.out.println(path5.resolve("Test.java"));
```

###### 标准化路径
```java
Path path8 = Paths.get("/data//work/./luciuschina/just-test/docs/tmp");
//输出：/data/work/luciuschina/just-test/docs/tmp
System.out.println(path8.normalize());
```


## Scatter/Gather or Vectored I/O
在Java NIO中，Channel提供了一个重要的能力，被称为scatter/gatter或者Vectored I/O。它是一个简单而强大的功能。可以使用write()方法将一组buffers中的数据写入到一个channel中，或者使用read()方法将一个channel中的数据读取到一组buffers中。

#### Scattering Reads
“scattering read”用于从单个channel中读取数据到多个buffers中。

![image](https://www.javatpoint.com/core/javanio/images/nio-tutorial11.png)

#### Gathering Writes
“gathering write”用于将数据从多个buffers中写入到单个channel中。

![image](https://www.javatpoint.com/core/javanio/images/nio-tutorial12.png)

#### Basic Scatter/Gather Example

```java
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

```

## Data Transfer between Channels
如果两个Channel中其中一个是FileChannel，那么直接可以从Channel到Channel传输数据。这种传输方式是经过优化的，效率较高。
FileChannel类中有以下两个方法，用于channels间的数据传输：
1. FileChannel.transferTo()方法
2. FileChannel.transferFrom()方法

#### Basic Channel to Channel Data Transfer Example
```java
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
```

## Java NIO Selector
在Java NIO中selector类似于一个多路复用器，用于管理多个channels，这些channel处于非阻塞模式。selector可以检查一个或多个channels，并且决定哪个channel准备进行通信,例如读或写。

#### Selector的用处是什么
selector使用单个线程管理多个channels，因此只需要很少的线程就能管理这些channels。操作系统切换线程的开销是昂贵的。因此使用selector可以提升系统的性能。
下图展示了一个线程使用Selector来处理3个Channels：

![在这里插入图片描述](https://www.javatpoint.com/core/javanio/images/nio-tutorial13.png)

#### 创建一个Selector
我们可以通过调用Selector.open()方法来创建一个Selector,如下：

```java
Selector selector = Selector.open();
```

