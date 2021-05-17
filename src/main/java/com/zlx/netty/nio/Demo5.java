package com.zlx.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * transferTo() 实现文件拷贝
 */
public class Demo5 {
    public static void main(String[] args) {
        copyFile();
//        copyFile2();
    }

    public static void copyFile(){
        try {
            // 可读可写的chanel
//            FileChannel channel = new RandomAccessFile(file, "rw").getChannel();

            // FileInputStream 只能读的chanel
            FileChannel from = new FileInputStream("word.txt").getChannel();
            // FileOutputStream 只能写的chanel
            FileChannel to = new FileOutputStream("word_cooy.txt").getChannel();

            // 将from读取的文件从 0的位置开始 全部写入到 to的文件
            from.transferTo(0, from.size(), to);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * transferTo() 一次最大传送2G文件超出无法传送 
     */
    public static void copyFile2(){
        try {
            FileChannel from = new FileInputStream("word.txt").getChannel();
            FileChannel to = new FileOutputStream("word_cooy.txt").getChannel();
            
            long size = from.size();
            for (long left = size; left > 0;) {
                // 下次剩余的 == 本次剩余的 - 本次传输的
                // 起始位置 == 总量 - 剩余的
                left -= from.transferTo(size - left, left, to);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
