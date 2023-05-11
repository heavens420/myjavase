package com.zlx.GOF.chainofresponsibility.demo1;

import java.util.Objects;

/**
 * 经理审批
 */
public class Manager implements BudgetHandler {

    private BudgetHandler nextHandler;


    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        Objects.requireNonNull(nextHandler);
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if (amount < 5000) {
            System.out.println("小组长审批：这点小钱太少了，准了");
            return true;
        }
        System.out.println("经理审批："+amount+"块钱太多了，我问问领导意见");
        return nextHandler.handle(amount);
    }
}
