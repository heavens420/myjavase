package com.zlx.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * POI 操作excel表格
 *  03版
 */


public class execl03 {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\420\\Downloads\\Documents";
        //1 创建工作簿 （excel文件）
        Workbook workbook = new HSSFWorkbook();

        //2 创建工作表（sheet）
        Sheet sheet = workbook.createSheet("mysheet");

        //3 创建一行 (此处选择第 4 行）
        Row row = sheet.createRow(3);

        //4 创建一个单元格 （此处选择第 5 个单元格）
        Cell cell = row.createCell(4);

        //5 为单元格填值
        cell.setCellValue("你好啊");

        //6 将数据写入excel 并导出到本地
        FileOutputStream fileOutputStream = new FileOutputStream(path + "/mysheet07.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        System.out.println("生成完毕");
    }
}
