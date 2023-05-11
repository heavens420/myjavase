package com.zlx.GOF.factory.abstractfactorymethod;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/26
 * @Version 1.0
 */
public interface AbstractFactory {
    Desk createDesk();
    Chair createChair();
}
