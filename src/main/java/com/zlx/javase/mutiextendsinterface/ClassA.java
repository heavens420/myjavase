package com.zlx.javase.mutiextendsinterface;

public class ClassA implements InterfaceA{
    @Override
    public void sayA() {

    }

    @Override
    public void say() {
        System.out.println("jjjjjjjj");
    }

    @Override
    public void sayB() {

    }

    @Override
    public void sayKK() {

    }

    @Override
    public void sayC() {

    }

    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.say();
    }
}
