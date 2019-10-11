package com.zlx.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress("localhost",9999));
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while(true){
            client.getOutputStream().write(sc.next().getBytes());
        }

    }
}
