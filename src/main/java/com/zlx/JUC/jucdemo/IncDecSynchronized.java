package com.zlx.JUC.jucdemo;

/**
 *
 * 创建n个线程执行+1 操作
 * 创建n个线程执行-1 操作
 * 每个线程执行10次循环
 * 加减交替执行
 * 最终结果为初始值
 *
 *
 * 多线程判断时 要用 while 不能用 if 防止出现虚假唤醒
 *
 * 若使用if判断则可能会出现 ...1 2 1 0 1 ... 出现 2 的情况 不符合条件
 *  原因分析：
 *      当初始值为 1 时 进入inc时 等待并唤醒其它线程 ，恰巧又唤醒了inc线程，继续等待，
 *      之后只能唤醒dec线程（这里inc总共创建了两个，且全部处于阻塞状态），
 *      dec执行之后唤醒了inc线程，按理来说已经执行了inc 下一个唤醒的如果是inc就会阻塞，
 *      但是，之前已经阻塞了两个inc线程，执行完一个还有一个处于阻塞状态，
 *      这时，如果再次唤醒inc线程的话，就会立刻执行inc方法，因为该inc的if判断已经结束，
 *      所以会出现两次执行inc方法
 *      若是 使用while循环判断，则再次唤醒的线程不管之前是否已经判断过一次，本次仍会判断，
 *      所以就不会出现连续两次调用inc或dec，这样正是解决了 虚假唤醒。
 */
public class IncDecSynchronized {

    int i = 0;
    public synchronized void inc() throws InterruptedException {
        while (i != 0){
            this.wait();
        }
        i++;
        System.out.println(Thread.currentThread().getName()+" "+i);
        this.notifyAll();
    }

    public synchronized void dec() throws InterruptedException {
        while (i == 0){
            this.wait();
        }
        i--;
        System.out.println(Thread.currentThread().getName()+" "+i);
        this.notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        IncDecSynchronized operation = new IncDecSynchronized();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    operation.dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}
