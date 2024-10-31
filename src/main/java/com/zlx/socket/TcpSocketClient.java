package com.zlx.socket;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpSocketClient {
    public static void main(String[] args){
        Thread t = new Thread(){
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 47708);
                    OutputStream sos = socket.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(sos));
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100);
                        bufferedWriter.write(i);
                        int result = 1;
                        result *= i;
                    }
                    sos.flush();
                    //特别注意要加上这个,不然服务端的socket.getInputStream().read()一直获取不了-1.
//                    socket.shutdownOutput();
                    socket.close();
                    System.out.println("client socket finish!");
                } catch (Exception e) {
                    System.out.println(e);
                }
            };
        };
        t.start();
    }
}
