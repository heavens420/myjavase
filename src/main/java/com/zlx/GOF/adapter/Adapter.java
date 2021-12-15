package com.zlx.GOF.adapter;

/**
 * 适配器本身 实现USB接口和外设接口连接
 */
public class Adapter implements Target{

    private KeyBoard keyBoard;

    public Adapter(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    @Override
    public void handleReq() {
        keyBoard.request();
    }
}
