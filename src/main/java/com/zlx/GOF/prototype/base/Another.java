package com.zlx.GOF.prototype.base;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Another {
    private String name;
    private String age;

    public Another(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
