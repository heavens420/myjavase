package com.zlx.netty.nio;

import com.zlx.netty.nio.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.zlx.netty.nio.ByteBufferUtil.debugAll;

/**
 * 非阻塞服务
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
//        server3();
        writeServer();
    }

    /**
     * 非阻塞模式
     * @throws IOException
     */
    public static void server() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 创建服务器
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置服务器为非阻塞 默认为阻塞
        serverSocketChannel.configureBlocking(false);

        // 绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(8888));

        // 保存chanel的集合
        List<SocketChannel> list = new ArrayList<SocketChannel>();

        while (true) {
            SocketChannel channel = serverSocketChannel.accept();

            if (channel != null) {
                log.info("已连接...{}",channel);
                // 设置为非阻塞模式 默认为阻塞模式
                channel.configureBlocking(false);
                list.add(channel);
            }

            for (SocketChannel socketChannel : list) {
                int read = socketChannel.read(buffer); // 读不到客户端的数据 返回0
                if (read > 0) {
                    buffer.flip();
                    ByteBufferUtil.debugAll(buffer);
                    buffer.clear();
                    log.info("after read: {}",socketChannel);
                }
            }
        }

    }

    /**
     * Selector 非阻塞模式
     *      Selector: 保存所有的事件
     *      SelectorKeys: 保存已经发生的事件，已经发生的事件必须处理，或者手动取消，否则Selector会认为该事件一直存在 不停的循环该事件
     * @throws IOException
     */
    public static void server2() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        // 为chanel注册selector 该selector管理此chanel
        SelectionKey serverKey = serverSocketChannel.register(selector, 0, null);
//        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        // 只关注可处理事件
        serverKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {
            // 客户端发起连接请求 会自动触发accept() 阻塞直到绑定事件发生
            int count = selector.select();
            // 设置等待绑定事件 超时时间
//            selector.select(1000);
            // 不阻塞 立刻返回 不管有没有绑定事件发生
//            selector.selectNow();
            log.info("count:{}", count);

            if (count < 1) {
                continue;
            }
            // 获取所有事件
            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            // 遍历绑定事件 逐一处理
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 判断事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 必须处理 不处理 则 selector不会阻塞
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
//                    SelectionKey scKey = sc.register(selector, 0, null);
                    // 只关注读事件
//                    scKey.interestOps(SelectionKey.OP_READ);
                    sc.register(selector, SelectionKey.OP_READ);

                    log.info("sc:{}", sc);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int read = socketChannel.read(buffer);
                    if (read == -1) { // -1表示客户端正常断开连接 客户端正常端口会产生一个读事件
                        // 不处理事件可以取消该事件 防止selector一直循环
                        key.cancel();
                        socketChannel.close();
                    }else {
                        buffer.flip();
                        ByteBufferUtil.debugRead(buffer);
                    }

                }
                // 处理完 移除事件 否则报空指针异常
                keyIterator.remove();
            }
        }
    }

    /**
     * 解决粘包半包问题
     * @throws IOException
     */
    public static void server3() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        // 为chanel注册selector 该selector管理此chanel
        SelectionKey serverKey = serverSocketChannel.register(selector, 0, null);
//        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        // 只关注可处理事件
        serverKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {
            // 客户端发起连接请求 会自动触发accept() 阻塞直到绑定事件发生
            int count = selector.select();
            // 设置等待绑定事件 超时时间
//            selector.select(1000);
            // 不阻塞 立刻返回 不管有没有绑定事件发生
//            selector.selectNow();
            log.info("count:{}", count);

            if (count < 1) {
                continue;
            }
            // 获取所有事件
            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            // 遍历绑定事件 逐一处理
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                // 判断事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 必须处理 不处理 则 selector不会阻塞
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    // 将buffer作为附件 与selectionKey唯一绑定
                    SelectionKey scKey = sc.register(selector, 0, buffer);
//                    sc.register(selector, SelectionKey.OP_READ);
                    // 只关注读事件
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("sc:{}", sc);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
//                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    // 获取绑定的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int read = socketChannel.read(buffer);
                    if (read == -1) { // -1表示客户端正常断开连接 客户端正常端口会产生一个读事件
                        // 不处理事件可以取消该事件 防止selector一直循环
                        key.cancel();
                        socketChannel.close();
                    }else {
//                        buffer.flip();
//                        ByteBufferUtil.debugRead(buffer);
                        // 解决半包粘包问题
                        split(buffer);
                        // 解决 buffer扩容问题
                        // 当起始位置 == 最大位置时 说明buffer已经满了 此时要扩容buffer
                        if (buffer.position() == buffer.limit()) {
                            ByteBuffer newByteBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                            buffer.flip();
                            // 将原buffer中的内容复制到新的扩容后的buffer中
                            newByteBuffer.put(buffer);
                            // 将新的扩容后的buffer作为附件与selectionKey唯一绑定
                            key.attach(newByteBuffer);
                        }
                    }

                }
                // 处理完 移除事件 否则报空指针异常
                keyIterator.remove();
            }
        }
    }

    /**
     * 大量数据写给客户端时 解决效率低下问题
     * @throws IOException
     */
    public static void writeServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        // selector注册chanel并绑定只关注事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            // 获取selector注册的所有事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 移除已经处理的事件
                keyIterator.remove();
                if (key.isAcceptable()) {
                    // 获取建立连接的客户端
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 设置异步
                    socketChannel.configureBlocking(false);
                    // 注册selector并设置关注事件
                    SelectionKey socketKey = socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(16));

                    StringJoiner joiner = new StringJoiner("");
                    for (int i = 0; i < 7000000; i++) {
                        joiner.add("a");
                    }
                    ByteBuffer buffer = StandardCharsets.UTF_8.encode(joiner.toString());
                    if (buffer.hasRemaining()) {
                        // 设置原来关注的事件(即上面设置的读事件)和现在关注的事件
                        socketKey.interestOps(socketKey.interestOps() + SelectionKey.OP_WRITE);
                        // 返回实际写入的字节数
//                        int writeCount = socketChannel.write(buffer);
//                        log.info("write:{}",writeCount);
                        // 把未读完的数据挂在socketKey上
                        socketKey.attach(buffer);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    int writeCount = socketChannel.write(buffer);
                    log.info("write:{}",writeCount);

                    // 读完则清理掉写事件及关联的buffer
                    if (!buffer.hasRemaining()) {
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }
        }
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
