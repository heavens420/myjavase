package com.zlx.io;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.val;
import org.apache.ibatis.annotations.Param;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 读文件
 */
public class ReadFile {

//    static String path = "C:\\workspace\\java\\myproject\\myjavase\\src\\main\\java\\com\\zlx\\java8features\\LambdaTest.java";
//    static String path = "C:\\Users\\heave\\Desktop\\云网应用数据制作-版本文件.txt";
    static String path = "C:\\Users\\heave\\Desktop\\昌吉市第七小学骨干教师、学科带头人管理考核方案.doc";

    public static void main(String[] args) throws IOException {
        readFileByLine();
//        readAllFiles();
//        readFiles2();
//        readJsonText();
//        readFiles4();
//        readFiles5();
    }

    public static void readJsonText() throws IOException {
        String fatherOrg = "";

        String json = Files.readAllLines(Paths.get(path)).stream().collect(Collectors.joining());
//        System.out.println("json:=========================>\n"+json);
        Map map = JSONObject.parseObject(json, Map.class);
        JSONArray jsonArray = JSONArray.parseArray(map.get("hrorgs").toString());
        System.out.println("array:========================>\n"+jsonArray.toString());

        List<Map> list = new ArrayList<Map>();

        // 查找根节点
        for (Object o : jsonArray) {
            Map node = JSONObject.parseObject(o.toString(), Map.class);
            String pkFatherorg = node.get("pk_fatherorg").toString();
            // 顶层标识
            if (pkFatherorg.equals(fatherOrg)) {
                list.add(node);
            }
        }

    }

    /**
     * 按行读取文件  流式读取适用于大小文件 不会内存溢出
     */
    public static void readFileByLine(){
        try(Stream<String> stream = Files.lines(Paths.get(path))){
//            System.out.println(stream.collect(Collectors.joining()).toString());
            stream.forEachOrdered(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 一次读取所有文件  注意内存溢出
     * @throws IOException
     */
    public static void readAllFiles() throws IOException {
        List<String> list = Files.readAllLines(Paths.get(path));
        list.forEach(System.out::println);
//        System.out.println(String.valueOf(list));

    }

    /**
     * 不会内存溢出
     * @throws IOException
     */
    public static void readFiles2() throws IOException {
//        val seekableByteChannel = Files.newBufferedReader(Paths.get("","",""));
        val bufferedReader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8).read();
        System.out.println(bufferedReader);
    }

    /**
     * 一次读取全部 注意内存溢出
     * @throws IOException
     */
    public static void readFiles3() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(content);
    }

    /**
     * 使用NIO读取文件
     * @throws IOException
     */
    public static void readFiles4() throws IOException {
        Path path = Paths.get("C:\\workplace\\mine\\myjavase\\src\\main\\java\\com\\zlx\\io\\IoDemo.java");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) != -1) {
            // 切换为读模式
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
        }
        channel.close();
    }


    /**
     *  基于MappedByteBuffer的读 将文件映射到内存 避免了不必要的IO 速度更快
     * @throws IOException
     */
    public static void readFiles5() throws IOException {
        String path = "C:\\workplace\\mine\\myjavase\\src\\main\\java\\com\\zlx\\io\\IoDemo.java";
//        Path path = Paths.get("C:\\workplace\\mine\\myjavase\\src\\main\\java\\com\\zlx\\io\\IoDemo.java");

        FileChannel chanel = new RandomAccessFile(path,"rw").getChannel();
        MappedByteBuffer buffer = chanel.map(FileChannel.MapMode.READ_WRITE, 0, chanel.size());
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        chanel.close();
    }
}
