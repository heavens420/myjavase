package com.zlx.javase.reflect;

import com.zlx.java8features.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Slf4j
public class Demo {
    public static void main(String[] args) throws IllegalAccessException {
        getAndSetFields();
    }

    public static void getAndSetFields() throws IllegalAccessException {
        User user = new User(444, null);

        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(user);
            log.info("before {}:{}", name,value);
            if (value == null) {
                field.set(user, "666");
                value = field.get(user);
            }
            log.info("after {}:{}", name,value);
        }
    }
}
