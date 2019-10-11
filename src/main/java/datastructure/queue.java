package datastructure;


import java.beans.beancontext.BeanContext;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class queue {
    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);
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

class ArrayQueue{
    private int []queue ;
    private int front;  //队列头
    private int rear;  //队列尾
    private int maxsize; //最大容量
    public ArrayQueue(int maxsize){
        this.maxsize = maxsize;
        queue = new int[maxsize];
        front = -1;
        rear = -1;
    }
    //判断队列是否满
    public boolean isFull(){
        return maxsize - 1 == rear;
    }

    //判断队列是否位空
    public boolean isEmpty(){
        return front == rear;
    }

    //添加数据
    public void add(int num){
        if (isFull()){
            System.out.println("队列已满");
        }else {
            queue[++rear] = num;
            System.out.println("添加成功");
        }
    }

    //删除数据 ，数据出队
    public int get()  {
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front = front+1;
        return queue[front];
    }

    //取栈顶数据，不删除
    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return queue[front+1];
    }

    //遍历队列
    public void show(){
        if (isEmpty()){
            System.out.println("队列已空");
            return;
        }
//        for (int i:
//             queue) {
//            System.out.print(i+"\t");
//        }
        for (int i = 0; i < queue.length; i++) {
            System.out.printf("queue[%d] = %d \t",i,queue[i]);

        }
    }
}
