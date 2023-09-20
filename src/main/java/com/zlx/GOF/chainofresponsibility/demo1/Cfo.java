package com.zlx.GOF.chainofresponsibility.demo1;

/**
 * 首席财务官审批
 */
public class Cfo implements BudgetHandler{

    private  BudgetHandler nextHandler;

    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if (amount < 10000) {
            System.out.println("CFO审批：这点小钱，太少了，准了");
            return true;
        }
        System.out.println("CFO审批："+amount+"块钱申请太多了，少申请点吧，不给批");
        // 最后一个环节 没有下个环节了
        return false;
    }
}
