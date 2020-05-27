package com.zlx.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyListener  extends AnalysisEventListener<DataModel> {


    List<DataModel> list  = new ArrayList<>();
    int num = 0;

    //每读一行都会调用一次invoke方法
    @Override
    public void invoke(DataModel dataModel, AnalysisContext analysisContext) {
        num++;
        list.add(dataModel);
        if (list.size() % 2 == 0){

        }
        System.out.println(dataModel+" "+num);
    }

    //读完文档 调用此方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
