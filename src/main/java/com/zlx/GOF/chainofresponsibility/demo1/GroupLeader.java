package com.zlx.GOF.chainofresponsibility.demo1;

import java.util.Objects;

/**
 * 小组领导审批
 */
public class GroupLeader implements BudgetHandler{

    private  BudgetHandler nextHandler;



    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        Objects.requireNonNull(nextHandler);

        // 设置下一环节处理
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if (amount < 1000) {
            System.out.println("小组长审批：小钱，准了");
            return true;
        }
        System.out.println("小组长审批："+amount+"块钱太多了，批不了，找我领导审批");
        return nextHandler.handle(amount);
    }
}
