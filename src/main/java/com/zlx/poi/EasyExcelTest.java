package com.zlx.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 使用easyExcel 操作excel
 * 写数据
 */

public class EasyExcelTest {

    private String book;

    public static void main(String[] args) throws IOException {

        Map<String, String> map = new HashMap<>(8);

        String templateFill = "C:\\Users\\420\\Downloads\\Documents/template-fill-duixiang.xlsx";
        String templateFillList = "C:\\Users\\420\\Downloads\\Documents/template-fill-list.xlsx";
        String path = "C:\\Users\\420\\Downloads\\Documents/easyexcel.xlsx";
        String template = "excelTemplate/write_template.xlsx";
        String template1 = "src/main/resources/excelTemplate/write_template.xlsx";
        String outPut = "src/main/resources/excelOutPut/result1.xlsx";
        //根据对象导出数据
//        EasyExcel.write(path,DataModel.class).sheet("model2").doWrite(writeData());

        //根据对象填充 list集合
//        EasyExcel.write(path).withTemplate(templateTable).sheet().doFill(writeData());

//        根据对象填充 list集合   另一种写法
//        ExcelWriter excelWriter = EasyExcel.write(path).withTemplate(templateTable).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        excelWriter.fill(writeData(), writeSheet);
//        //关闭 流
//        excelWriter.finish();

//        write1(path);
//        write2(path);
//        write3(path);
//        write4(path);
//        write5(path);
//        write6(path);
        write8(outPut,template1);
//        write9(outPut,template1);
//        write10(path,templateFill);
//        write11(path,templateFillList);
    }

    /**
     * 模拟数据库中查出的数据列表
     * @return
     */
    public static List<DataModel> writeData() {
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataModel dataModel = new DataModel();
            dataModel.setTitle("This is title" + i);
            dataModel.setDate(new Date());
            dataModel.setNumber(i + 1000);
            list.add(dataModel);
        }
        return list;
    }

    /**
     * 只写入指定字段或只不写入指定字段
     *
     * @param path
     */
    public static void write1(HttpServletResponse response, String path) throws IOException {
        Set exinclude = new HashSet();
        exinclude.add("date");
        // 仅排除date字段
        EasyExcel.write(path,DataModel.class).excludeColumnFiledNames(exinclude).sheet(0).doWrite(writeData());

        Set include = new HashSet();
        include.add("date");
        // 仅导出date字段
        EasyExcel.write(path, DataModel.class).includeColumnFiledNames(include).sheet(0).doWrite(writeData());

        // 导出
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
//        String downloadFileName = URLEncoder.encode(path, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        String downloadFileName = path;
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + downloadFileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(),DataModel.class).sheet("fileName").doWrite(writeData());
    }

    /**
     * 生成复杂标题
     *
     * @param path
     */
    public static void write2(String path) {
        EasyExcel.write(path, ComplexTitle.class).sheet(0).doWrite(null);
    }

    /**
     * 多次将同一个对象写入到同一个sheet
     *
     * @param path
     */
    public static void write3(String path) {
        ExcelWriter writer = null;
        try {
            writer = EasyExcel.write(path, DataModel.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("write3").build();
            for (int i = 0; i < 5; i++) {
                // 此处可以是 从数据库中查询出的数据  此处自己模拟
                List<DataModel> dataModels = writeData();
                writer.write(dataModels, writeSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.finish();
            }
        }
    }

    /**
     * 将同一个对象多次写入到不同的sheet
     * @param path
     */
    public static void write4(String path){
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(path,DataModel.class).build();
            for (int i = 0; i < 5; i++) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i,"write"+i).build();
                List<DataModel> list = writeData();
                excelWriter.write(list,writeSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null){
                excelWriter.finish();
            }
        }
    }

    /**
     * 不同的对象写入到不同的额sheet
     * @param path
     */
    public static void write5(String path){
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(path).build();
            for (int i = 0; i < 5; i++) {
                // sheetName每次都变  DataModel每次都变（这里为了简单 用了同一个class）
                WriteSheet writeSheet = EasyExcel.writerSheet(i,"model"+i).head(DataModel.class).build();
                List<DataModel> list = writeData();
                excelWriter.write(list,writeSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 导出图片到excel
     * @param path
     */
    public static void write6(String path){

        try {
            List<DataModelImage> list = new ArrayList<>();
            DataModelImage dataModelImage = new DataModelImage();

            String imagePath = "C:\\Users\\420\\Pictures\\Saved Pictures/kitty.jpg";

            dataModelImage.setFile(new File(imagePath));
//            dataModelImage.setString(imagePath);
            dataModelImage.setInputStream(new FileInputStream(imagePath));
            dataModelImage.setUrl(new URL("https://raw.githubusercontent.com/alibaba/easyexcel/master/src/test/resources/converter/img.jpg"));
            list.add(dataModelImage);

            EasyExcel.write(path,DataModelImage.class).sheet(0).doWrite(list);
        } catch (Exception e) {

        } finally {

        }
    }

    /**
     * excel下载 导出
     * @param path
     * @param response
     */
//    @RequestMapping("/download")
//    public void write7(String  path,HttpServletResponse response){
//        EasyExcel.write(response.getOutPutStream,Download.class).sheet(0).doWrite(writeData());
//    }

    /**
     * 根据模板写
     * @param fileName
     * @param template
     */
    public static void write8(String fileName,String template) throws IOException {
        Resource resource = new ClassPathResource(template);
        EasyExcel.write(fileName,DataModel.class).withTemplate(template).sheet(0).doWrite(writeData());
    }

    /**
     * 根据模板进行填充
     * @param fileName
     * @param template
     */
    public static void write9(String fileName,String template){
        DataModel dataModel = new DataModel();
        dataModel.setTitle("niahoa");
        dataModel.setDate(new Date());
        dataModel.setNumber(90000);
        // 不需要写 实体类
        EasyExcel.write(fileName).withTemplate(template).sheet().doFill(dataModel);
    }

    /**
     * 根据模板填充（map形式）
     * @param fileName
     * @param template
     */
    public static void write10(String fileName,String template){
        Map map = new HashMap(8);
        map.put("title","niaho");
//        map.put("date",new Date());
        map.put("date","2020-09-09");
        map.put("number",999.99);
        EasyExcel.write(fileName).withTemplate(template).sheet().doFill(map);
    }

    public static void write11(String fileName,String template){

        // 直接填充 一次将所有数据加载到内存
//        EasyExcel.write(fileName).withTemplate(template).sheet(0).doFill(writeData());

        // 多次填充  节省内存
        ExcelWriter writer = null;
        try {
            writer = EasyExcel.write(fileName).withTemplate(template).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
            writer.fill(writeData(),writeSheet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.finish();
            }
        }
    }
}
