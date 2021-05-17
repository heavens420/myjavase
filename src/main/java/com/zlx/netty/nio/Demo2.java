package com.zlx.netty.nio;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.zlx.netty.nio.ByteBufferUtil.debugAll;

/**
 * 字符串转ByteBuffer
 */
public class Demo2 {
    public static void main(String[] args) {
        String2ByteBuffer();
        String2ByteBuffer2();
        String2ByteBuffer3();
    }

    // 方式一
    public static void String2ByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(16);
        String str = "nihaoa,zheshijie";
        buffer.put(str.getBytes(StandardCharsets.UTF_8));
        debugAll(buffer);

        // 将ByteBuffer转换为 字符串
        // 先切换为读模式
        buffer.flip();
        String string = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(string);
    }

    /**
     * 方式二: 这种方法会在写入buffer结束后自动切换为读模式,即position置为0
     */
    public static void String2ByteBuffer2(){
        String str = "nihaoa,zheshijie";
        final ByteBuffer buffer = StandardCharsets.UTF_8.encode(str);
        debugAll(buffer);
    }

    // 同方式二
    public static void String2ByteBuffer3(){
        String str = "nihaoa,zheshijie";
        final ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        debugAll(buffer);
    }
}
