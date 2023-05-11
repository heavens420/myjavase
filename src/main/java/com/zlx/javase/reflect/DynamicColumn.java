package com.zlx.javase.reflect;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.Map;


/**
 * 动态增加对象属性
 *
 * java8 之后对反射进行了功能限制 有些代码无法正常运行
 * 解决方案：增加jvm参数 --add-opens java.base/java.lang=ALL-UNNAMED
 * 即 java --add-opens java.base/java.lang=ALL-UNNAMED xxx.class
 */
public class DynamicColumn {
    public static Object getTarget(Object dest, Map<String, Object> addProperties) {
        PropertyUtilsBean propertyUtilsBean =new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        Map<String, Class> propertyMap = Maps.newHashMap();
        for(PropertyDescriptor d : descriptors) {
            if(!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // add extra properties
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        // new dynamic bean
        DynamicBean dynamicBean =new DynamicBean(dest.getClass(), propertyMap);
        // add old value
        propertyMap.forEach((k, v) -> {
            try{
                // filter extra properties
                if(!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        // add extra value
        addProperties.forEach((k, v) -> {
            try{
                dynamicBean.setValue(k, v);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }

    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         * @return
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator =new BeanGenerator();
            if(null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Student {
        private String name;
        private String email;
    }

    /**
     * 简化版 动态生成对象属性
     */
    public static void generate() {
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("id", Long.class);
        generator.addProperty("addr", String.class);
        Object obj = generator.create();
        BeanMap beanMap = BeanMap.create(obj);
        beanMap.put("id", 89L);
        beanMap.put("addr", "beijing");
        System.out.println(JSONObject.toJSONString(obj));
    }

    public static void main(String[] args) throws Exception{
        Student student = Student.builder().name("jack").email("xy123zk@163.com").build();
        System.out.println(student.toString());
        Map<String,Object> properties = Maps.newHashMap();
        properties.put("address","浙江杭州");
        properties.put("age",26);
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(getTarget(student,properties));
        String jsonString = JSONObject.toJSONString(getTarget(student, properties));
//        System.out.println(json);
        System.out.println(jsonString);

        System.out.println("-----------------------------------------");
        generate();
    }
}
