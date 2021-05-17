package com.zlx.netty.nio;


import java.nio.ByteBuffer;

import static com.zlx.netty.nio.ByteBufferUtil.debugAll;

/**
 * 黏包和半包现象的解决办法
 */
public class Demo4 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("hello world,\nzhangsan,\nni".getBytes());
        split(buffer);
        buffer.put("haoa,\n".getBytes());
        split(buffer);
    }

    public static void split(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            // get(i) 不移动指针 为了后面从头开始读内容写入target 如果用get() 移动指针,则不能读取前面的数据
            if (buffer.get(i) == '\n') {
                // 获取开始到 \n的字符长度
                int length = i + 1 - buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                // 将第 n 段字符写入新的buffer n = 1,2,3...
                for (int j = 0; j < length; j++) {
                    target.put(buffer.get());
                }
                debugAll(target);
            }
        }
        // 解决半包,未读完的 保留在buffer中 为下一次读取
        buffer.compact();
    }
}
