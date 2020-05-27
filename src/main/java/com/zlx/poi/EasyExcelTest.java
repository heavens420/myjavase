package com.zlx.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import javax.naming.ldap.HasControls;
import java.util.*;

/**
 * 使用easyExcel 操作excel
 *  写数据
 */

public class EasyExcelTest {
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<String,String >();

        String template = "C:\\Users\\420\\Downloads\\Documents\\excel/easyexcel-template.xlsx";
        String templateTable = "C:\\Users\\420\\Downloads\\Documents\\excel/easyexcel-template-列表.xlsx";
        String path = "C:\\Users\\420\\Downloads\\Documents\\excel/easyexcel.xlsx";
        //根据对象导出数据
//        EasyExcel.write(path,DataModel.class).sheet("model").doWrite(writeData());

        //根据对象填充(单行数据）
        DataModel dataModel = new DataModel();
        dataModel.setTitle("niahoa");
        dataModel.setRiqi(new Date());
        dataModel.setNumber(90000);
//        EasyExcel.write(path).withTemplate(template).sheet().doFill(dataModel);

        //根据map填充(单行数据）
        map.put("title","nihaoa");
        map.put("riqi","2020-02-03");
        map.put("number","12");
//        EasyExcel.write(path).withTemplate(template).sheet().doFill(map);

        //根据对象填充 list集合
//        EasyExcel.write(path).withTemplate(templateTable).sheet().doFill(writeData());

        //根据对象填充 list集合   另一种写法
        ExcelWriter excelWriter = EasyExcel.write(path).withTemplate(templateTable).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(writeData(),writeSheet);
        //关闭 流
        excelWriter.finish();
    }

    //创建要写入的数据 ， 项目中为 数据库查询出的数据
    public static List<DataModel> writeData(){
        List<DataModel> list = new ArrayList<DataModel>();
        for (int i = 0; i < 10; i++) {
            DataModel dataModel = new DataModel();
            dataModel.setTitle("This is title"+i);
            dataModel.setRiqi(new Date());
            dataModel.setNumber(i+1000);
            list.add(dataModel);
        }
        return list;
    }
}
