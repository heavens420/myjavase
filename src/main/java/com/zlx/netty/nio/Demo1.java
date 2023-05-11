package com.zlx.netty.nio;

import lombok.extern.slf4j.Slf4j;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import static com.zlx.netty.nio.ByteBufferUtil.debugAll;

/**
 * flip:切换为读模式 此时position会置为起始位置 limit置为读取结果的最后一个索引位置
 * clear：重新写 清空buffer position置为0 limit置为capacity
 * compact：写模式 buffer中有一部分数据不想清空 而想接着写 此时会把buffer中已经读的数据清空 未读的移动到最前面最后一个数据位置为position limit为capacity
 */

@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        ReadFiles();
//        ReadAndWrite();
//        ReadAndWrite2();
    }

    /**
     * 读取文件并打印文件内容
     */
    public static void ReadFiles() {
        try {
            // file chanel
            FileChannel channel = new FileInputStream(".\\note-2020-08-25.txt").getChannel();
            // 缓冲区 一次最多读取10个字节
            final ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                // 向buffer中写数据
                final int len = channel.read(buffer);
//                buffer.put(((byte) 97));
                log.info("从buffer中读取到的字节数：{}", len);
                // len为-1 表示读完
                if (len == -1) {
                    break;
                }
                // 打印buffer的内容
                // 切换为读模式
                buffer.flip();

                // 循环读取 直到读完
                while (buffer.hasRemaining()) {
                    // .get() 不带参数表示按字节读取
                    final byte b = buffer.get();
//                    final int write = channel.write(buffer);
                    log.info("实际读取的字节数:{}", ((char) b));
                }
                // 切换为写模式，
                buffer.clear();
//                debugAll(buffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *重新从头开始读buffer
     */
    public static void ReadAndWrite(){
        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 向buffer中写数据
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        // 切换为读模式
        buffer.flip();

        // 从头开始读 一次读四个字节
        buffer.get(new byte[4]);
        debugAll(buffer);

        // 重新从头开始读
        // rewind() 将 position重置为0 实现重复从头读
        buffer.rewind();
//        debugAll(buffer);
        System.out.println(((char) buffer.get()));
    }

    /**
     * 设置标志位 reset()会将 position置为标志位的值 并从标志位开始重新读buffer
     * get(index) 不会移动position指针
     * get()会移动position
     */
    public static void ReadAndWrite2(){
        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 向buffer中写数据
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        // 切换为读模式
        buffer.flip();

        // 读取两个字节
        System.out.println(((char) buffer.get()));  // a
        System.out.println(((char) buffer.get()));  // b

        // 标记当前字节位置  即b的位置
        buffer.mark();  // 标记b的位置

        // 继续读取两个字节
        System.out.println(((char) buffer.get())); // c
        System.out.println(((char) buffer.get())); // d

        // 想要从上次标记的地方重新开始读 即b开始读(不包含b,即不包含标记的位置)
        buffer.reset();  // 从标记的位置(mark())开始读
        System.out.println(((char) buffer.get())); // c
        System.out.println(((char) buffer.get())); // d

        // 重置position为0
        buffer.rewind();

        //获取指定索引的值
//        get(index) 不会影响position的值
        System.out.println(((char) buffer.get(2)));
        debugAll(buffer);  // position的值依然为0
    }
}
