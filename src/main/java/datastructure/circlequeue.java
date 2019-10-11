package datastructure;

import java.util.Scanner;

public class circlequeue {
    public static void main(String[] args) {
        Cqueue queue = new Cqueue(4);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        char i = ' ';
        while (flag){
            i = sc.next().charAt(0);
            switch (i){
                case 'a':
                    System.out.println("输入一个数");
                    queue.add(sc.nextInt());
                    break;
                case 'g': try {
                    int s =  queue.get();
                    System.out.println("出队的数据是："+s);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                    break;
                case 's':
                    queue.show();
                    break;
                case 'p':
                    try {
                        int s = queue.peek();
                        System.out.println("队首数据： "+s);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    flag = false;
                    System.out.println("退出");
                    break;
                default:
                    break;
            }
        }
    }
}

class Cqueue {
    private int maxsize;
    private int front;
    private int cear;
    private int [] queue;

    public Cqueue(int maxsize){
        this.maxsize = maxsize;
        queue = new int[maxsize];
    }

    //判空 当队头追到队尾 队列空
    public boolean isEmpty(){
        return front == cear;
    }

    //判满  当队尾追到对头时(实际未追上，相差一个数据）  队列满
    public boolean isFull(){
        return (cear+1)%maxsize == front;
    }

    //添加数据
    public void add(int num){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        queue[cear] = num;
        cear = (cear+1)%maxsize;
        System.out.println("添加成功");
    }

    //删除数据  数据出队
    public int get(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int temp = queue[front];
        front = (front+1)%maxsize;
        return temp;
    }

    //取队首元素
    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return queue[front];
    }

    //遍历队列
    public void show(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
//        for (int i:
//             queue) {
//            System.out.print(i+"\t");
//        }
        for (int i = front; i < ((cear-front+maxsize)%maxsize)+front; i++) {
            System.out.printf("queue[%d] = %d\t",i%maxsize,queue[i%maxsize]);
        }
    }

}