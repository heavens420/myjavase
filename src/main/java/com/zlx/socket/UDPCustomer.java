package com.zlx.socket;

import java.net.*;

public class UDPCustomer {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket(8888);
        String s = "你好啊，这世界 12345  二哈的世界你不懂  66746gsgsgssgags";
        byte[] datas = s.getBytes("UTF-8");
        DatagramPacket packet = new DatagramPacket(datas,0,datas.length,new InetSocketAddress("localhost",9999));
        client.send(packet);
        client.close();
    }
}
