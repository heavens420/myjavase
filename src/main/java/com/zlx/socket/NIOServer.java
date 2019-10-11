package com.zlx.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(); //创建服务器
        server.bind(new InetSocketAddress(9999)); //绑定端口，IP不写默认本机 localhost
        byte[] datas = new byte[1024]; //创建接收数据容器
        while (true){

            Socket socket = server.accept(); //接收客户端的连接
            int len = socket.getInputStream().read(datas);//获取数据长度
            String data = new String(datas,0,len);//将数据转换为String类型
            System.out.println(data);//打印输出客户端传进的数据
        }
    }
}
