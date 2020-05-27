package com.zlx.poi;

import com.alibaba.excel.EasyExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * easyExcel 读数据
 */

public class EasyExcelRead {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\420\\Downloads\\Documents\\excel/easyexcel.xlsx";

//        FileInputStream fileInputStream = new FileInputStream(path);

        EasyExcel.read(path,DataModel.class,new MyListener()).sheet(0).doRead();
    }
}
