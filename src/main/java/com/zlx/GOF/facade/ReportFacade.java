package com.zlx.GOF.facade;


/**
 * 外观模式，封装复杂实现  提供简单接口
 */
public class ReportFacade {
    public void buy(){
        PaySys paySys = new PaySys();
        OrderSys orderSys = new OrderSys();
        TransportSys transportSys = new TransportSys();

        // 先支付成功
        paySys.pay();
        // 再创建订单
        orderSys.createOrder();
        // 然后发货
        transportSys.transport();
        // ...
    }
}
