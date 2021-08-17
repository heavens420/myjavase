package com.zlx.netty.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static com.zlx.netty.nio.ByteBufferUtil.debugAll;


/**
 * 分散读,集中写
 */
public class Demo3 {
    public static void main(String[] args) throws IOException {
        batchRead();
//        batchWrite();
    }

    public static void batchRead() throws FileNotFoundException {
        String file = "C:\\Users\\420\\Desktop\\test.txt";
        try (FileChannel channel = new RandomAccessFile(file, "rw").getChannel()) {
            // 将文件存入多个buffer
            ByteBuffer buffer1 = ByteBuffer.allocate(10);
            ByteBuffer buffer2 = ByteBuffer.allocate(20);
            ByteBuffer buffer3 = ByteBuffer.allocate(20);

            // 一次读多个buffer
            channel.read(new ByteBuffer[]{buffer1, buffer2, buffer3});
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();


//            debugAll(buffer1);
//            debugAll(buffer2);
//            debugAll(buffer3);
            System.out.println("new String(buffer1.array(),0,buffer1.limit()) = " + new String(buffer1.array(), 0, buffer1.limit()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void batchWrite() throws IOException {
        final ByteBuffer buffer = StandardCharsets.UTF_8.encode("nihaoa");
        final ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("zheshijie");
        final ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("很好");

        FileChannel channel = new RandomAccessFile("word.txt", "rw").getChannel();
        channel.write(new ByteBuffer[]{buffer, buffer1, buffer2});

    }
}
