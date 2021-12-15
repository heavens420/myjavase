package com.zlx.GOF.adapter;

/**
 * 客户端类 看作电脑     电脑通过USB接口连接键盘鼠标等
 */
public class Client {
    public void connect(Target target) {
        // 电脑的USB先连接适配器 通过适配器连接外设
        target.handleReq();
    }

    public static void main(String[] args) {
        Client client = new Client();
        KeyBoard keyBoard = new KeyBoard();
        Target target = new Adapter(keyBoard);
        client.connect(target);
    }
}
