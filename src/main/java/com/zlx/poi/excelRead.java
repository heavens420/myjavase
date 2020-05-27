//package com.zlx.poi;
//
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
///**
// * 读取excel数据
// */
//
//public class excelRead {
//    public static void main(String[] args) throws IOException {
//        String path = "C:\\Users\\420\\Downloads\\Documents/计算机16级重修课统计及解决方案（第三版-反馈统计 ）.xlsx";
//
//        FileInputStream inputStream = new FileInputStream(path);
//
//        Workbook workbook = new XSSFWorkbook(inputStream);
//
//        //获取第一个sheet 也可以通过名称
//        Sheet sheet1 = workbook.getSheetAt(0);
//
////        Row row = sheet1.getRow(1);
//
////        Cell cell = row.getCell(0);
//
////        String stringCellValue = cell.getStringCellValue();
//
////        System.out.println(stringCellValue);
//        //获取sheet 有数据的行数
//        int rows = sheet1.getPhysicalNumberOfRows();
//        for (int j = 0; j < rows; j++) {
//            Row row = sheet1.getRow(j);
//            if (row != null) {
//                //获取当前行 有多少（单元格）列有数据
//                int len = row.getPhysicalNumberOfCells();
//                for (int i = 0; i < len; i++) {
//                    Cell cell = row.getCell(i);
//                    if (cell != null) {
//                        CellType cellType = cell.getCellType();
//
//                        switch (cellType) {
//                            case BLANK:
//                                System.out.print("");
//                                break;
//                            case STRING:
//                                System.out.print(cell.getStringCellValue() + "\t" + "|");
//                                break;
//                            case NUMERIC:
////                                if (HSSFDateUtil.isCellDateFormatted(cell)){
////                                    Date dateCellValue = cell.getDateCellValue();
////                                }
//                                System.out.print(cell.getNumericCellValue() + "\t" + "|");
//                                break;
//                            default:
//                                System.out.print("error" + "\t" + "|");
//                        }
////                    String stringCellValue = cell.getStringCellValue();
////                    System.out.print(stringCellValue+"\t"+"|");
//                    }
//                }
//                System.out.println();
//            }
//        }
//
//
//    }
//}
