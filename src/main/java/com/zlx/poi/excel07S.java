package com.zlx.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class excel07S {
    public static void main(String[] args) throws IOException {
        String path =  "C:\\Users\\420\\Downloads\\Documents/";

        long currentTime = System.currentTimeMillis();
//        test03(path);
//        test07(path);
        test07Super(path);
        long endTime = System.currentTimeMillis();
        System.out.println((double)(endTime - currentTime)/1000);
    }


    //03版生成65536行数据时间 4-5秒
    public static void test03(String path) throws IOException {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("03");

        for (int i = 0; i < 65536; i++) {
            Row row = sheet.createRow(i);
            for (int i1 = 0; i1 < 25; i1++) {
                Cell cell = row.createCell(i1);
                cell.setCellValue(i1);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(path + "03test.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }

    //07版生成65536行数据时间 30-33秒
    public static void test07(String path) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("03");

        for (int i = 0; i < 65536; i++) {
            Row row = sheet.createRow(i);
            for (int i1 = 0; i1 < 25; i1++) {
                Cell cell = row.createCell(i1);
                cell.setCellValue(i1);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(path + "07test.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }

    //07加速版版生成65536行数据时间 4.6秒
    public static void test07Super(String path) throws IOException {
        Workbook workbook = new SXSSFWorkbook();

        Sheet sheet = workbook.createSheet("03");

        for (int i = 0; i < 65536; i++) {
            Row row = sheet.createRow(i);
            for (int i1 = 0; i1 < 25; i1++) {
                Cell cell = row.createCell(i1);
                cell.setCellValue(i1);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(path + "07supertest.xlsx");
        workbook.write(fileOutputStream);
        //加速版会生成临时文件 清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        fileOutputStream.close();
        workbook.close();
    }
}
