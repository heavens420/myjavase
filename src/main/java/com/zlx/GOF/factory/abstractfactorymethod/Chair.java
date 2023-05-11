package com.zlx.GOF.factory.abstractfactorymethod;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/26
 * @Version 1.0
 */
public class Chair {
    private String cpu;
    private String ram;

    private String keyBoard;
    private String display;

    public Chair(String cpu) {
        this(cpu, null);
    }

    public Chair(String cpu, String ram) {
        this(cpu, ram,null);
    }

    public Chair(String cpu, String ram,String keyBoard) {
        this(cpu, ram,keyBoard,null);
    }

    public Chair(String cpu, String ram, String keyBoard, String display) {
        this.cpu = cpu;
        this.ram = ram;
        this.keyBoard = keyBoard;
        this.display = display;
    }

}
