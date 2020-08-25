package com.zlx.poi;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 * @Desc 图片上传实体类
 */
@Data
@ContentRowHeight(100)
@ColumnWidth(100/8)
public class DataModelImage {

    // 如果是String 类型 则必须指定转换器 string默认转换为string类型
    @ExcelProperty(value = "String",converter = MyListener.class)
    private String string;

    @ExcelProperty(value = "inputstream")
    private InputStream inputStream;

    @ExcelProperty(value = "Byte")
    private Byte[] byteArray;

    @ExcelProperty(value = "file")
    private File file;

    @ExcelProperty(value = "url")
    private URL url;
}
