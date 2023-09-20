package com.zlx.GOF.chainofresponsibility.demo1;

public interface BudgetHandler {
    /**
     * 设置链条的下一个环节
     * @param nextHandler
     */
    void setNextHandler(BudgetHandler nextHandler);

    /**
     * 当前环节处理
     */
    boolean handle(int amount);

}
