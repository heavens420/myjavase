package com.zlx.others;

public class FandS {
    public void say(){
        System.out.println("fa");
    }
}
class Son extends FandS{
    @Override
    public void say(){
        System.out.println("son");
    }
}

class Test{
    public static void main(String[] args) {
        FandS s = new Son();
        s.say();
    }
}