package com.zlx.poi;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 继承 AnalysisEventListener 为读
// 实现 Converter 为写
public class MyListener  extends AnalysisEventListener<DataModel> implements Converter<String> {

    List<DataModel> list  = new ArrayList<>();
    int num = 0;
    final Long NUM = 10000L;

    /**
     * 每读一行都会调用一次invoke方法，可用于批量将数据持久化到数据库
     * @param dataModel
     * @param analysisContext
     */
    @Override
    public void invoke(DataModel dataModel, AnalysisContext analysisContext) {
        num++;
        list.add(dataModel);
        // 将数据持久化到数据库中
        if (list.size()  > NUM){
            //saveData();
        }
        System.out.println(dataModel+" "+num);
    }

    /**
     * 异常处理
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (exception instanceof ExcelDataConvertException){
            System.out.println("数据解析异常，但是会继续解析下一行");
        }
    }

    /**
     * 此方法会一行行的返回表头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("解析到头数据"+headMap);
        super.invokeHeadMap(headMap, context);
    }

    /**
     * 读完文档 调用此方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取结束");
    }

    /**
     * 备注，超链接等额外信息，额外信息默认不读，需要单独设置读取
     * @param extra
     * @param context
     */
    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        System.out.println("读取到一条额外信息");

        switch (extra.getType()){
            case COMMENT:
                System.out.println("批注"+extra.getRowIndex()+"行"+extra.getColumnIndex()+"列："+extra.getText());break;
            case HYPERLINK:
                System.out.println("超链接"+extra.getRowIndex()+"行"+extra.getColumnIndex()+"列："+extra.getText());break;
            default:
                System.out.println("其它或未知类型");
        }
    }


    //_______________------------------------读取时的日期转换和其它数据格式化-----------------_____________________---
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 读的时候会用
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 读的时候加上 hhh 三个字母
        return "hhh"+cellData.getStringValue();
    }

    /**
     * 写的时候会用
     * @param s
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 写的时候加上自定义 三个字
        return new CellData("自定义：："+s);
    }
}
