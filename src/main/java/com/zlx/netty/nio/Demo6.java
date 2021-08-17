package com.zlx.netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * walkFileTree() 遍历文件,此为访问者模式
 */
@Slf4j
public class Demo6 {
    public static AtomicInteger dirCount = new AtomicInteger();
    public  static AtomicInteger fileCount = new AtomicInteger();

    public static void main(String[] args) throws IOException {
        testWalkFileTree();
//        countFiles();
    }

    public static void testWalkFileTree() throws IOException {
        Files.walkFileTree(Paths.get("C:\\Users\\420\\Desktop\\source\\文档\\netty\\黑马Netty教程源码资料\\讲义\\Netty-讲义"),
                new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        dirCount.incrementAndGet();
                        StringJoiner joiner = new StringJoiner("-");
                        for (int i = 0; i < dirCount.intValue(); i++) {
                            joiner.add("-");
                        }
                        log.info("dir==>{}{}",dir,joiner);
                        return super.preVisitDirectory(dir, attrs);
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        fileCount.incrementAndGet();
                        log.info("file==>{}",file);
                        return super.visitFile(file, attrs);
                    }
                });
        log.info("dirCount == {}",dirCount);
        log.info("fileCount == {}",fileCount);
    }

    public static void countFiles() throws IOException {
        fileCount.set(0);
        Files.walkFileTree(Paths.get("C:\\Users\\420\\Desktop\\source\\文档\\netty\\黑马Netty教程源码资料\\讲义\\Netty-讲义"),
                new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.toString().endsWith(".md")) {
                            fileCount.incrementAndGet();
                            log.info(".md文件 ==> {}",file);
                        }
                        return super.visitFile(file, attrs);
                    }
                });
        log.info(".md文件的数量:{}",fileCount);
    }
}
