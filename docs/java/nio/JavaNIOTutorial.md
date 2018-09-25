# [Java NIO Tutorial](https://javapapers.com/java/java-nio-tutorial/)

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
```
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
```
ByteBuffer byteBuffer = ByteBuffer.allocate(512);
```
2. 对buffer进行flip(),准备读
```
byteBuffer.flip();
```
3. 从channel中将数据读到buffer中
```
int numberOfBytes = fileChannel.read(byteBuffer);
```
4. 从buffer中读取数据
```
char c = (char)byteBuffer.get();
```

#### 如何将数据写入NIO Buffer
1. 创建一个buffer，并设置capacity
```
ByteBuffer byteBuffer = ByteBuffer.allocate(512);
```
2. 将数据放入Buffer中
```
byteBuffer.put((byte) 0xff);
```

上面两个例子只是从Buffer中进行读写的简单例子。实际上有各种Buffer可用，各种各样的读/写方法。需要根据自己需求来选择。

#### NIO Buffer Read Write例子
```
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
