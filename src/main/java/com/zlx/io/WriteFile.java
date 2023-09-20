//package com.zlx.io;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//
//public class WriteFile {
//    static String path = "./testWrite.txt";
//
//    public static void main(String[] args) {
//
//    }
//
//    /**
//     * 管道流方式写文件 可设置新建文件还是追加内容到旧文件
//     */
//    public static void writeFile1(){
//        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
//            bufferedWriter.write("你好啊 这世界！");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 直接写
//     * @throws IOException
//     */
//    public static void writeFile2() throws IOException {
//        Files.writeString(Paths.get(path), "nihaoa zheshijie !!",StandardOpenOption.APPEND);
//
////        Files.write(Paths.get(path),"".getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE_NEW);
//    }
//
//    /**
//     * 传统方式
//     * 写对象可以用 ObjectOutputStream
//     * 写二进制数据用 ByteArrayOutputStream
//     */
//    public static void writeFile3() {
//        try (FileOutputStream outputStream = new FileOutputStream(path)){
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//            bufferedWriter.write("jfasfjasf");
//            bufferedWriter.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
