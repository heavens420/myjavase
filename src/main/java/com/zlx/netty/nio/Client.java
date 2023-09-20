package com.zlx.netty.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
//        client1();
//        client2();
        writeClient();
    }

    /**
     * 客户端
     * @throws IOException
     */
    public static void client1() throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 9999));
//        client.write(StandardCharsets.UTF_8.encode("hello world"));
        System.out.println("debug");
    }

    public static void client2() throws IOException {
        Socket socket = new Socket("localhost", 9999);
        socket.getOutputStream().write("nihao".getBytes());
        System.in.read();
    }

    public static void writeClient() throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 9999));
        int count = 0;

        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            int read = client.read(buffer);
            count += read;
            buffer.clear();

            log.info("read:{}", count);
        }
    }
}
