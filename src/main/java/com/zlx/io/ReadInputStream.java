package com.zlx.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 读取 inputStream流
 */
public class ReadInputStream {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 通过调用shell命令 返回inputstream
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("ls");
        Process process = builder.start();
        InputStream inputStream = process.getInputStream();
        read04(inputStream);
//        read03(inputStream);
//        read02(inputStream);
//        read01(inputStream);

        // 等待命令执行完
        int i = process.waitFor();
        System.out.println(i);
    }

    public static void read01(InputStream in) throws IOException {
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
    }

    public static void read02(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void read03(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = in.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        System.out.println(bos);
    }

    public static void read04(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        int read = reader.read();
        System.out.println(read);
        reader.close();
    }
}
