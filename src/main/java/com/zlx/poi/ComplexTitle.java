package com.zlx.poi;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/24
 * @Version 1.0
 */
@Data
public class ComplexTitle {
    @ExcelProperty(value = {"主标题","日期标题"})
    private Date date;

    @ExcelProperty(value = {"主标题","字符串标题"})
    private String string;

    @ExcelProperty(value = {"主标题","数字标题"})
    private Double aDouble;
}
