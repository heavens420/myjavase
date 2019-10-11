package com.zlx.test;

import javax.sound.midi.Soundbank;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

import static java.util.concurrent.Executors.*;

public  class TestDemo {
    public static void main(String[] args) {


        ExecutorService es =  newCachedThreadPool();
        es.execute(new Thread(()-> System.out.println("nihao")));
        new Runnable(){
            @Override
            public void run() {
                System.out.println("00000000");
            }
        }.run();

        ((Runnable)  ()-> System.out.println("1111")).run();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2222");
            }
        }).start();

        new Thread(()-> System.out.println("9999999")).start();

        new Thread("Thread33"){
            @Override
            public void run() {
                System.out.println("3333");
            }
        }.start();


        ((Runnable)()-> System.out.println("66666666666")).run();
        Runnable s = ()->{
            System.out.println("8888");
        };
        new Thread(s).start();

        new Thread(new A(),"Thread").start();
        new Thread(new B(),"Runnable").start();


        es.shutdown();

        while (true){
            if (1==1){
                System.out.println("你好吗这世界");
                break;
            }
            System.out.println("我好呢 这世界");
        }
    }




}

class A extends Thread{
    @Override
    public void run() {
        System.out.println("555555");
    }
}

class B implements Runnable{

    @Override
    public void run() {
        System.out.println("777777");
    }
}
