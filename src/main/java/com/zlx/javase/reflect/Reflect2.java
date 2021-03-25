package com.zlx.javase.reflect;

import com.google.common.reflect.Reflection;
import com.zlx.poi.EasyExcelTest;

import java.lang.reflect.InvocationTargetException;

/**
 * 通过反射获取对象
 *
 * 通过反射创建对象
 */
public class Reflect2 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1、通过运行时类的属性获取对象实例
        Class cls1 = EasyExcelTest.class;
        System.out.println(cls1);

        // 2、通过运行时类对象调用 .getClass()
        EasyExcelTest easyExcelTest = new EasyExcelTest();
        Class cls2 = easyExcelTest.getClass();
        System.out.println(cls2);

        // 3、通过Class的静态方法 forName(classPath)
        Class cls3 = Class.forName("com.zlx.poi.EasyExcelTest");
        System.out.println(cls3);

        // 4、通过类加载器获取对象实例
        ClassLoader classLoader = EasyExcelTest.class.getClassLoader();
        Class cls4 = classLoader.loadClass("com.zlx.poi.EasyExcelTest");
        System.out.println(cls4);

        // 1、通过newInstance()创建对象
        EasyExcelTest instance = ((EasyExcelTest) cls1.newInstance());
        System.out.println(instance);

        // 2、通过通过构造器和newInstance()
        EasyExcelTest instance2 = ((EasyExcelTest) cls2.getConstructor().newInstance());
        System.out.println(instance2);
    }
}
