package com.zlx.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/5
 * @Version 1.0
 * @Desc
 */
public class ExcelWrite07 {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\420\\Downloads\\Documents/";
        exportExcel(path);
    }

    public static void exportExcel(String path) throws IOException {

        // 模拟从数据库查出来的集合对象
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataModel dataModel = new DataModel();
            dataModel.setDate(new Date());
            dataModel.setNumber(i * 4325 + 234);
            dataModel.setTitle("duixiang" + i);
            dataModel.setId(i + 837429);
            list.add(dataModel);
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet write = workbook.createSheet("write");

        // 单元格 锁定  当设置单元格锁定的时候， 默认锁定所有单元格，不锁定同理
        CellStyle unlockCellStyle = workbook.createCellStyle();
        unlockCellStyle.setLocked(false);

        // 单元格不锁定
        CellStyle lockCellStyle = workbook.createCellStyle();
        lockCellStyle.setLocked(true);

        String[] title = "ID,日期,Number,String".split(",");


        // 设置表头 标题
        Row row = write.createRow(0);
        // 单独设置 是否锁定该行
        row.setRowStyle(lockCellStyle);
        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }

        // 插入集合数据
        for (int i = 1; i < list.size() + 1; i++) {
            // 创建行
            // 从第二行开始插入，第一行是标题
            Cell cell = null;
            Row row1 = write.createRow(i);
            // 单独设置是否锁定改行
            row1.setRowStyle(unlockCellStyle);

            // 创建单元格 将字段依次插入到单元格
            cell = row1.createCell(0);
            cell.setCellValue(list.get(i - 1).getId());

            cell = row1.createCell(1);
            cell.setCellValue(list.get(i - 1).getDate());

            cell = row1.createCell(2);
            cell.setCellValue(list.get(i - 1).getNumber());

            cell = row1.createCell(3);
            cell.setCellValue(list.get(i - 1).getTitle());
        }

        FileOutputStream fileOutputStream = new FileOutputStream(path + "poiWrite.xslx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();

    }
}
