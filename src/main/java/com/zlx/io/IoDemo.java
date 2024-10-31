package com.zlx.io;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IoDemo {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\420\\Desktop\\source/aaa.txt";
        String filePath2 = "C:\\Users\\420\\Desktop\\source/io2.txt";

//        justRead();
//       ioTest01();
//        ioTest2();
//        ioTest3();
//        ioTest4();
//        ioTest5();
//        ioTest6(filePath,filePath2);
//        ioTest7(filePath,filePath2);


//        beforeSendCmd(200);
//        System.out.println("============");
//        testAvaliable();
        JSONArray jsonArray = JSONObject.parseArray("[23]");
        Long aLong = jsonArray.getLong(0);
        Object o = jsonArray.get(0);
        System.out.println(aLong);
        System.out.println(o);

    }



    //字节流的 copy 文本
    public static void ioTest01(){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int i = 0;
        StringBuffer buffer = new StringBuffer();

        File filePath = new File("C:\\Users\\heave\\Desktop\\jar/io.txt");
        File dstPath = new File("C:\\Users\\heave\\Desktop\\jar/io2.txt");
        try {
            inputStream = new FileInputStream(filePath);
            outputStream = new FileOutputStream(dstPath,true);

            while ((i = inputStream.read()) != -1){
                outputStream.write(i);
                buffer.append((char) i);
                System.out.println(buffer);
                String replace = buffer.toString().replaceAll("¿ï¿¿", "");
                System.out.println("====="+replace);
            }
            outputStream.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void justRead() {
        File filePath = new File("C:\\Users\\heave\\Desktop\\jar/aaa.txt");
        File dstPath = new File("C:\\Users\\heave\\Desktop\\jar/bbb.txt");
        StringBuffer buffer = new StringBuffer();
        try (FileInputStream inputStream = new FileInputStream(filePath); FileOutputStream fileOutputStream = new FileOutputStream(dstPath)) {
            while (true) {
                int read = inputStream.read();
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(read);
                System.out.println("i==" + read);
                System.out.println("char=" + (char) read);
                buffer.append((char) read);
                System.out.println(buffer);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void testAvaliable() {
        File filePath = new File("C:\\Users\\heave\\Desktop\\jar/aaa.txt");
        File dstPath = new File("C:\\Users\\heave\\Desktop\\jar/bbb.txt");

        try(FileInputStream inputStream = new FileInputStream(filePath); FileOutputStream fileOutputStream = new FileOutputStream(dstPath)) {
            byte[] bytes = new byte[10];
            while (true) {
                int available = inputStream.available();
                if (available == 0) {
                    break;
                }
                int length = inputStream.read(bytes);
                if (length <= 0) {
                    break;
                }
                String ssss = new String(bytes, 0, length);
                log.info("ssss=【{}】",ssss);
                fileOutputStream.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ioTest2(){
        FileReader reader = null;
        FileWriter writer = null;
        int i = 0;

        String filePath = "C:\\Users\\420\\Desktop\\source/io.txt";
        String filePath2 = "C:\\Users\\420\\Desktop\\source/io2.txt";

        try {
            reader = new FileReader(filePath);
            writer = new FileWriter(filePath2,true);

            while ((i = reader.read()) != -1){
                writer.write(i);
            }
            writer.close();
            reader.close();
            System.out.println("copy successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ioTest3(){
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        int i = 0;

        String filePath = "C:\\Users\\420\\Desktop\\source/io.txt";
        String filePath2 = "C:\\Users\\420\\Desktop\\source/io2.txt";

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath2));

            while ((i = bufferedInputStream.read()) != -1){
                bufferedOutputStream.write(i);
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ioTest4(){
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        String s ;
        int i = 0;
        String filePath = "C:\\Users\\420\\Desktop\\source/io.txt";
        String filePath2 = "C:\\Users\\420\\Desktop\\source/io2.txt";

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(filePath2))));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath2)));
            while ((s = bufferedReader.readLine()) != null){
                bufferedWriter.write(s);
                bufferedWriter.newLine();
//                System.out.println(s);
            }

//            while ((i = bufferedReader.read()) != -1){
//                bufferedWriter.write(s);
//                bufferedWriter.newLine();
//                System.out.println(s);
//            }
            //用完关闭  否则 内存存满 复制写操作 提前结束
            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ioTest5(){
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        String s ;
        int i =0;

        String filePath = "C:\\Users\\420\\Desktop\\source/io.txt";
        String filePath2 = "C:\\Users\\420\\Desktop\\source/io2.txt";

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            bufferedWriter = new BufferedWriter(new FileWriter(filePath2));

            while ((s = bufferedReader.readLine()) != null) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量读取
    public static void  ioTest6(String beginPath, String endPath){
        FileInputStream fileInputStream ;
        FileOutputStream fileOutputStream;

        int len = 0;
        byte[] bytes = new byte[1024];

        try {
            fileInputStream = new FileInputStream(beginPath);
            fileOutputStream =new FileOutputStream(endPath);

            while ((len = fileInputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes);
            }

            fileOutputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //编码转换
    public static void ioTest7(String beginPath,String endPath){
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        int len = 0;
        byte[] bytes = new byte[1024*6];

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(beginPath));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(endPath));

            while (bufferedInputStream.read() != -1){
                bufferedOutputStream.write(bytes);
            }

            bufferedOutputStream.close();
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ToString
    static class Data{
        private String name;
        private int age;

//        public Data(String name, int age) {
//            this.name = name;
//            this.age = age;
//        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
