package com.zlx.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(9999);
        byte[] container = new byte[1024*60];
        DatagramPacket packet = new DatagramPacket(container,0,container.length);
        server.receive(packet);

        byte[] datas = packet.getData();
        String data = new String(datas, 0, datas.length);
        System.out.println(data);


//        String answer = "收到请求";
//        byte[] ans = answer.getBytes("UTF-8");
        server.close();
    }
}
