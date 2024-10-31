package com.zlx.drools.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookBean {
    private long id;

    private String name;

    private String price;
}
