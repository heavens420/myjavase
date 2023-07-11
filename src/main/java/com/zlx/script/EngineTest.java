package com.zlx.script;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.python.util.PythonInterpreter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * ScriptEngine使用
 */
@Slf4j
public class EngineTest {
    public static void main(String[] args) throws ScriptException {
        String engineName = "python";
        String script = "name = 'nihaoa'\n" +
                "print(name)\n" +
                "\n" +
                "print('值1', name)\n" +
                "\n" +
                "print('标识2', id(name))\n" +
                "\n" +
                "print('类型3', type(name))\n" +
                "\n" +
                "# 将二进制编码 转化为字符\n" +
                "print(chr(0b1001111011))\n" +
                "\n" +
                "# 将十进制编码转化为字符\n" +
                "print(chr(23068))\n" +
                "\n" +
                "# 将字符转化为 十进制编码\n" +
                "print(ord('娜'))";
        String script4 = "#!/bin/env python\n" +
                "# 列表的创建方式 两种\n" +
                "lst = ['we', 'fs', 83]\n" +
                "\n" +
                "lst2 = list(['ee', 'vv', 34])\n" +
                "\n" +
                "print(lst)\n" +
                "print(lst2)\n" +
                "\n" +
                "# 查找索引\n" +
                "print(lst.index('fs'))\n" +
                "\n" +
                "# 在指定索引范围查询 左闭右开 索引不存在  报错\n" +
                "# print(lst.index('hhh', 1, 2))\n" +
                "\n" +
                "# 根据索引获取元素\n" +
                "print(lst[0])\n" +
                "\n" +
                "lst3 = lst2.copy()\n" +
                "lst2.clear()\n" +
                "print(lst3)";

        String script2 = "python -V";
        String script3 = "#!/usr/bin/python\n" +
                "import sys\n" +
                "print sys.version \n" +
                "print sys.version_info";
        String path = "C:\\workplace\\python\\mine\\base\\base\\10列表.py";

//        testScriptEngine(engineName, script3);
//        testScriptEngine(script3);
//        testGraavlVM();
//        testRunTime(script3);
        testRuntime();
    }

    public static void getEngineList() {
        String pythonInterpreter = "C:\\Program Files\\Python310\\python.exe";

        System.setProperty("python.home", pythonInterpreter);
        ScriptEngineManager manager = new ScriptEngineManager();
        // 得到所有的脚本引擎工厂
        List<ScriptEngineFactory> factories = manager.getEngineFactories();

        for (ScriptEngineFactory factory : factories) {
            // 打印脚本信息
            System.out.printf("Name: %s%n" + "Version: %s%n" +
                            "Language name: %s%n" +
                            "Language version: %s%n" +
                            "Extensions: %s%n" +
                            "Mime types: %s%n" +
                            "Names: %s%n",
                    factory.getEngineName(),
                    factory.getEngineVersion(),
                    factory.getLanguageName(),
                    factory.getLanguageVersion(),
                    factory.getExtensions(),
                    factory.getMimeTypes(),
                    factory.getNames());
            // 得到当前的脚本引擎
            ScriptEngine engine = factory.getScriptEngine();
        }
    }

    public static void testScriptEngine(String engineName, String script) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(engineName);
        engine.eval(script);
    }

    public static void testScriptEngine(String script) {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.exec(script);

    }


    /**
     * runtime方式调用python脚本
     * python脚本放置于项目下
     */
    public static void testRuntime() {
        // 获取当前路径
        File directory = new File("");//设定为当前文件夹
        String dirPath = directory.getAbsolutePath();//获取绝对路径
        Process proc;
        try {
            // 配置环境变量 （本案例中测试 完全没用）
            String[] envp = new String[]{"Path:C:\\ProgramData\\anaconda3\\"};
            // 工作目录 （本案例中 完全没用）
            File file = new File("C:\\workplace\\python\\mine\\base\\base\\");
            // python解释器路径 默认系统配置的环境变量
            String pyPath = "C:\\ProgramData\\anaconda3\\python.exe ";
            // python脚本文件路径 默认项目目录  即C:\workplace\mine\myjavase
            String pyFilePath = dirPath + "\\10列表.py";
            System.out.println(pyFilePath);
            // 传给python的参数
            String argv1 = "一人之下";
            proc = Runtime.getRuntime().exec(pyPath + " " + pyFilePath + " " + argv1, envp, file);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getErrorStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println("-----" + line);
            }
            in.close();
            int result = proc.waitFor();
            proc.destroy();
            System.out.println("==========result:" + result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * runtime方式调用python脚本
     * 可指定脚本路径
     *
     * @param path 脚本路径
     */
    public static void testRuntime(String path) {

//        String[] arguments = new String[]{"C:\Program Files\Python310", "E://workspace/hello.py", "lei", "23"};
        String[] arguments = new String[]{"C:\\ProgramData\\anaconda3\\python.exe", path};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println("-----" + line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();
            process.destroy();
            System.out.println("result==========" + re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testGraavlVM() {
    }

    //        try (Context context = Context.create()) {
//            Value date = context.eval("js", "new Date().toString()");
//            System.out.println(date.asString());
//        }
    private static final String PYTHON = "python";
    private static final String PYTHON_PYTHON_PATH = "python.PythonPath";
    private static final String PYTHON_EXECUTABLE = "python.Executable";
    private static final String PYTHON_FORCE_IMPORT_SITE = "python.ForceImportSite";

    private static Context createContext(String modulePath) {
//        Engine engine = Engine.create();
//        Context context = Context.newBuilder(PYTHON).allowAllAccess(true).engine(engine)
//                .option(PYTHON_FORCE_IMPORT_SITE, "true")
//                .option(PYTHON_PYTHON_PATH, modulePath)
//                .option(PYTHON_EXECUTABLE, CustomizeConfig.getPythonExecutable()).build();
//        return context;
        return null;
    }

//    public static void main(String[] args) {
//        Context context = createContext();
//        Value bindings = context.getBindings(PYTHON);
//        bindings.putMember("a", 1);
//        bindings.putMember("b", 2);
//        int i = context.eval(PYTHON, "a+b").asInt();
//        System.out.println(i);
//    }

}
