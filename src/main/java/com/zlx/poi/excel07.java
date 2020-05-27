package com.zlx.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * POI 操作07excel
 */

public class excel07 {
    public static void main(String[] args) {
        String path = "C:\\Users\\420\\Downloads\\Documents/";

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("mysheet07");

        Row row = sheet.createRow(3);

        Cell cell = row.createCell(3);

        cell.setCellValue("wohenhao ");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "07.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("生成成功");
    }
}
