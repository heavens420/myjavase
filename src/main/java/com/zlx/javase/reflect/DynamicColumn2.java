package com.zlx.javase.reflect;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * org.apache.commons.beanutils
 */
public class DynamicColumn2 {


    public static void main(String[] args) throws Exception {
        System.out.println("----------------------setProperty------------------------------------------");
        Map<String, Object> map = new HashMap<>();
        map.put("age", 100);
        map.put("name", "zhangsan");
        DynamicClassTest dynamicColumn = setProperty(map, DynamicClassTest.class);
        System.out.println(dynamicColumn); // DynamicClassTest(name=zhangsan, age=100)

        System.out.println("----------------------copyBeanToBean------------------------------------------");
        DynamicClassTest2 test2 = new DynamicClassTest2("lisi", 20, "23fas@173.com");
        DynamicClassTest dynamicClassTest = copyBeanToBean(test2, dynamicColumn);
//        DynamicClassTest2 dynamicClassTest = copyBeanToBean(dynamicColumn,test2);
        System.out.println(dynamicClassTest); // DynamicClassTest(name=lisi, age=20)

        System.out.println("----------------------copyMapToBean------------------------------------------");
        DynamicClassTest dyn = new DynamicClassTest("wangwu", 90);
        DynamicClassTest dynTarget = copyMapToBean(map, dyn);
        System.out.println(dynTarget); // DynamicClassTest(name=zhangsan, age=100)
    }


    /**
     * 1 beanUtils 可以便于对javaBean的属性进行赋值。
     * @param propertyList
     * @param clazz
     */
    public static <T> T setProperty(Map<String, Object> propertyList, Class<T> clazz) throws Exception {
        T t = clazz.getDeclaredConstructor().newInstance();
        propertyList.forEach((k,v) -> {
            try {
                BeanUtils.setProperty(t, k, v);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return t;
    }

    /**
     * 2 beanUtils 将一个对象的属性复制到另一个对象 名称相同即替换 名称不同无操作
     * @param source
     * @param target
     * @return
     * @param <T>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> T copyBeanToBean(Object source, T target) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.copyProperties(target, source);
        return target;
    }


    /**
     * 3 beanUtils 将一个map中的键值对根据key和属性名为bean赋值 相同即替换 不同无操作
     * @param source
     * @param target
     * @return
     * @param <T>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> T copyMapToBean(Map<String,Object> source, T target) throws InvocationTargetException, IllegalAccessException{
        BeanUtils.populate(target,source);
        return target;
    }

    public static <T> T addPropertyToBean(Map<String,Object> source, T target){
        return null;
    }
}

