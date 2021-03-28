package com.zlx.javase.reflect;

import com.zlx.poi.EasyExcelTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/11
 * @Version 1.0
 * @Desc  反射用于获取运行时的类的相关属性和方法，并且可以对其进行修改（比如破坏单列）
 */
public class Reflect {

    public static void main(String[] args) {
        //获取 类中的所有方法和其父类（Object）的方法
        Method[] methods = EasyExcelTest.class.getMethods();

        // 获取当前类声明的方法
//        Method[] methods = EasyExcelTest.class.getDeclaredMethods();

        // 获取该类的类加载器
        ClassLoader classLoader = EasyExcelTest.class.getClassLoader();

        // 获取该类的方法，不带包路径
        String simpleName = EasyExcelTest.class.getSimpleName();

        // 获取该类上的注解
        Annotation[] annotations = EasyExcelTest.class.getAnnotations();

        // 获取该类的路径和名称
        String canonicalName = EasyExcelTest.class.getCanonicalName();

        // 获取该类中声明的全局变量
        Field[] declaredFields = EasyExcelTest.class.getDeclaredFields();

        // 获取 该类的父类
        Class<? super EasyExcelTest> superclass = EasyExcelTest.class.getSuperclass();


//        System.out.println(superclass);
//        System.out.println(Arrays.toString(declaredFields));
//        System.out.println(canonicalName);
//        System.out.println(classLoader.getParent());
        Stream.of(methods).forEach(System.out::println);
        System.out.println(methods.length);
    }
}
