package com.zlx.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.metadata.ReadSheet;
//import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * easyExcel 读数据
 */

public class EasyExcelRead {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\420\\Downloads\\Documents\\excel/easyexcel.xlsx";

        String path2 = "C:\\Users\\420\\Downloads\\Documents/easyexcel.xlsx";

        String path3 = "C:\\Users\\heave\\Desktop\\mysource\\宗调-ITSM\\生产指挥\\两类专线报表\\开通数据统计日\\全网开通数据统计日清单2023年6月4日.xls";

//        FileInputStream fileInputStream = new FileInputStream(path);

        // 读取指定sheet（单个）
        EasyExcel.read(path3, new MyListener()).sheet(0).doRead();

        //读取多行头  默认读取一行头 可以设置读取多行头
//        EasyExcel.read(path3,DataModel.class,new MyListener()).sheet(0).headRowNumber(1).doRead();

        // 直接返回读取的数据列表
//        List<DataModel> DataModelList = EasyExcel.read(new FileInputStream(path3)).head(DataModel.class).sheet().headRowNumber(2).doReadSync();

        // 读取额外类型  额外类型默认不读 需要单独设置读取
//        EasyExcel.read(path2,DataModel.class,new MyListener()).extraRead(CellExtraTypeEnum.COMMENT).sheet(0).doRead();
        //读取所有sheet
//        EasyExcel.read(path2, DataModel.class, new MyListener()).doReadAll();

        //读取多个sheet（多个）
//       readMoreSheet(path2);

//        web 中的读取上传excel
//        upload(file);


    }

    public static void readMoreSheet(String path){
        ExcelReader excelReader = null;
        try {

            excelReader = EasyExcel.read(path).build();

            // 实际开发 不能使用相同的 Listener
            ReadSheet readSheet1 = EasyExcel.readSheet(0).head(DataModel.class).registerReadListener(new MyListener()).build();
            ReadSheet readSheet2 = EasyExcel.readSheet(1).head(DataModel.class).registerReadListener(new MyListener()).build();

            // 两个sheet必须同时传进去，否则，对于03版 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelReader != null){
                //读的时候 会创建临时文件 不要忘了关闭 否则磁盘会崩
                excelReader.finish();
            }
        }
    }

//    @RequestMapping("/upload")
//    @ResponseBody
//    public static String upload(MultipartFile file) throws IOException{
//        EasyExcel.read(file.getInputStream(),FileUpLoad.class,new MyListener()).sheet().doRead();
//        return "success";
//    }
}
