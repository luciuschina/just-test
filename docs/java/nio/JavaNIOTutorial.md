# [Java NIO Tutorial](https://javapapers.com/java/java-nio-tutorial/)

#### 标准IO和NIO之间的不同：
首先，标准IO是基于流的，NIO是面向buffer的。面向Buffer的操作处理数据比较灵活，数据首先读取到缓存中，然后进行处理，所以Buffer中的数据可以反复被读取，而基于流的IO操作就不能这样操作。

其次，标准IO是阻塞IO，NIO是非阻塞IO。在标准IO中，线程会阻塞直到完成IO操作。而在NIO中允许非阻塞操作。如果IO操作现在不能获取数据，那么线程可以做一些其他的事情而不需要处于阻塞模式。使用Selector和Channel，一个线程可以管理多个Channel，可以并行化进行IO操作。Channel是Java NIO提供用于访问本地I/O机制的网关。我们需要使用Buffer与Channel进行交互，所以Channel就像两个实体进行I/O的桥梁。

## Channel
在Java NIO中，channel用于I/O传输。Channel就像一个管道，将数据从缓存和另一端的实体中进行传输。Channel从实体中读取数据，将它放到Buffer块中，用于消费。类似地，我们需要将数据写入到Buffer块中，然后通过Channel将数据传输到另一端。Buffer是提供给Channel发送和接收数据的端点。

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