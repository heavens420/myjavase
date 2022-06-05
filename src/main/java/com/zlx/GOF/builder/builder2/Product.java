package com.zlx.GOF.builder.builder2;

import lombok.Data;
import lombok.ToString;

/**
 * 具体产品零件
 */
@Data
@ToString
public class Product {
    private String arm;
    private String eye;
    private String leg;
}
