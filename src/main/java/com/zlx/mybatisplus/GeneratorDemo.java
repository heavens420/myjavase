package com.zlx.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GeneratorDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.80.169:3306/idc_cloud?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "mysql2015";
        String outPath = "C:\\workplace\\mine\\myjavase\\src\\main\\java\\com\\zlx\\mybatisplus\\code";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Zhao Long Long") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.gccloud.idc.entity.iotManage") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("rf_zk_zx_monthly_statistics_inter") // 设置需要生成的表名
                            .addTablePrefix("rf_zk_zx_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
