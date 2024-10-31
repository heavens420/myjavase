package com.zlx.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TcpSocketServer {
    public static void main(String[] args) {
        try {
            System.out.println("TcpSocketServer start!");
            ServerSocket serverSocket = new ServerSocket(47708);
            while (true) {
                System.out.println("TcpSocketServer waiting accept!");
                Socket socket = serverSocket.accept();
                System.out.println("TcpSocketServer accept and dealing!");
                InputStream sis = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sis));
                if (bufferedReader.ready()) {
                    System.out.println("bufferedReader.ready() = " + bufferedReader.ready());
                }
                int size = sis.available();
                byte[] data = new byte[512];
                char[] chars = new char[512];
                int available = sis.available();
                System.out.println("available = " + available);
                int k = -1;
                TimeUnit.SECONDS.sleep(5);
//                int read = sis.read(data);
                int end = bufferedReader.read(chars);
                StringBuilder builder = new StringBuilder();
//                for (char aChar : chars) {
//                    builder.append(aChar);
//                    System.out.println("chars =" + builder);
//                }
//                System.out.println(new String(chars, 0, end));
                System.out.println("chars = " + Arrays.toString(Arrays.copyOf(chars, end)));
//                System.out.println(" read = " + chars.toString());
//                for (byte datum : data) {
//                    System.out.println("datum = " +datum);
//                }
//                while((k = bufferedReader.read(chars)) != -1) {
//                    System.out.println("bufferedReader.ready() = " + bufferedReader.ready());
//                    System.out.println(" k = " + k + " size = " + size + " Collect form Client data = " + Arrays.toString(Arrays.copyOf(chars, k)));
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
