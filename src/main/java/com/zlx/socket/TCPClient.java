package com.zlx.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost",9999);
//        BufferedInputStream s = new BufferedInputStream(System.in);

//        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));

//        InputStream s = new DataInputStream(System.in);

//        String data  = s.toString();
        String s = "你好啊，这世界";
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        outputStream.writeUTF(s);
        outputStream.flush();
        outputStream.close();
        client.close();
    }
}
