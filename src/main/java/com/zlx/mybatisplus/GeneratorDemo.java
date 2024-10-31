package com.zlx.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GeneratorDemo {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://192.168.222.96:5433/root?currentSchema=public&PuseUnicode=true&characterEncoding=utf8&stringtype=unspecified&rewriteBatchedStatements=true";
        String username = "root";
        String password = "Root123#";
        String outPath = "C:\\workplace\\mine\\myjavase\\src\\main\\java\\com\\zlx\\mybatisplus\\code";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Zhao Long Long") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zlx.mockprocessdata") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("wf_activityinst,wf_transctrl,wf_processinst,so_api_inst,so_api_execute_rank,bm_cust_order") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
