package com.zlx.java8features;


import lombok.val;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8 读文件
 */
public class ReadFile {

    static String path = "C:\\workspace\\java\\myproject\\myjavase\\src\\main\\java\\com\\zlx\\java8features\\LambdaTest.java";

    public static void main(String[] args) throws IOException {
//        readFileByLine();
//        readAllFiles();
        readFiles2();
    }


    /**
     * 按行读取文件
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
     * 一次读取所有文件
     * @throws IOException
     */
    public static void readAllFiles() throws IOException {
        List<String> list = Files.readAllLines(Paths.get(path));
        list.forEach(System.out::println);
//        System.out.println(String.valueOf(list));

    }

    public static void readFiles2() throws IOException {
//        val seekableByteChannel = Files.newBufferedReader(Paths.get("","",""));
        val bufferedReader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8).read();
        System.out.println(bufferedReader);
    }
}
