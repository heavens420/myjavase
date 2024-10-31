package com.zlx.GOF.observe;


/**
 * 被观察者接口
 */
public interface Observable {
    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     * @param observer
     */
    void notifyObserver(Observer observer);

    void notifyObservers();
}
