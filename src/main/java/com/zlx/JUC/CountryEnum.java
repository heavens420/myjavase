package com.zlx.JUC;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * 定义枚举类
 */
public enum CountryEnum {
    QI(1,"齐国"),CHU(2,"楚国"),YAN(3,"燕国"),HAN(4,"韩国"),ZHAO(5,"赵国"),WEI(6,"魏国");

    @Getter
    private Integer code;
    @Getter
    private String name;

    CountryEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CountryEnum forName(int code){
        for (CountryEnum em:CountryEnum.values()) {
            if (code == em.getCode()){
                return em;
            }
        }
        return null;
    }
}
