package com.zlx.poi;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * easyExcel 实体类
 */

@Data
@ContentRowHeight(value = 15) //内容行高
@HeadRowHeight(value = 40) //表头行高
public class DataModel {
    @ExcelProperty(value = "字符串标题",index = 0) //表头列标题, index 为标题列的排列顺序 从0开始 不连续的index会出现空列
    @ColumnWidth(value = 20) //单元格宽度
    private String title;

    @ExcelProperty(value = "日期标题",index = 3)//1,2,index空缺 故 2，3列为空白列
    @ColumnWidth(20)
    @DateTimeFormat(value = "yyyy-MM-dd") //转换日期格式
    private Date riqi;

    @ExcelProperty(value = "数字标题",index = 6)
    @NumberFormat()  //格式化数字
    private Integer number;

    @ExcelProperty(value = "id")
    @ExcelIgnore //忽略此列
    private Integer id;
}
