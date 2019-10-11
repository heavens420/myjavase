package com.zlx.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer  {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();

        DataInputStream  inputStream = new DataInputStream(client.getInputStream());
        String datas = inputStream.readUTF();
        System.out.println(datas);
        inputStream.close();
        client.close();
        server.close();
    }
}
