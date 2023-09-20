package com.zlx.GOF.adapter.adapter1;

/**
 * 客户端类 看作电脑     电脑通过USB接口连接键盘鼠标等
 * 适配器适用于适配已有接口(Target)与待适配接口(KeyBoard)的桥梁
 * 所以适配器要实现已有接口(Target)并注入待适配接口(拿到待适配接口KeyBoard才能对其进行转换)
 * 使用的时候只需要创建Target的实现类对象 调用target的具体转换方法完成接口转换
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
