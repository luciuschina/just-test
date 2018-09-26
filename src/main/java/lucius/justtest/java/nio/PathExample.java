package lucius.justtest.java.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Lucius on 9/26/18.
 */
public class PathExample {
    public static void main(String[] args) throws IOException{
        //以下四句相等。Paths.get(...)的内部实现就是FileSystems.getDefault().getPath(...)
        Path path1 = FileSystems.getDefault().getPath("docs/tmp", "read.txt");
        Path path2 = FileSystems.getDefault().getPath("docs/tmp/read.txt");
        Path path3 = Paths.get("docs/tmp/read.txt");
        Path path4 = Paths.get("docs/tmp", "read.txt");

        //返回绝对路径
        Path currentPath1 = Paths.get("").toAbsolutePath();
        System.out.println(currentPath1);
        Path currentPath2 = Paths.get("docs/tmp/read.txt").toAbsolutePath();
        System.out.println(currentPath2);

        //拼接路径
        Path path5 = Paths.get("docs/tmp");
        //输出：docs/tmp/Test.java
        System.out.println(path5.resolve("Test.java"));

        //两个路径之间的相对位置
        Path path6 = Paths.get("/data/work/luciuschina/just-test");
        Path path7 = Paths.get("/data/work/luciuschina/just-test/docs/tmp");
        //输出docs/tmp
        System.out.println(path6.relativize(path7));
        //输出../..
        System.out.println(path7.relativize(path6));

        //标准化路径
        Path path8 = Paths.get("/data//work/./luciuschina/just-test/docs/tmp");
        //输出：/data/work/luciuschina/just-test/docs/tmp
        System.out.println(path8.normalize());

        //删除文件
        Files.delete(Paths.get("docs/tmp/a.txt"));
    }
}
