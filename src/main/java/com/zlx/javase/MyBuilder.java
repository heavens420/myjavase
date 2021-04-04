package com.zlx.javase;

//import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;


public class MyBuilder {
    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("h", "hh");


        MyEntity myEntity = MyEntity.builder()
                .name("nihao")
                .id(12)
                .age(99)
                .address("beijing")
                .email("hhhhh")
                .hobby(ImmutableList.of("jfs", "ff"))
                .build();
        System.out.println(myEntity);
    }
}

@Data
@Builder
@ToString
class MyEntity{
    private String name;
    private int id;
    private int age;
    private String address;
    private String email;
    private List<String> hobby;
    private Map home;


}