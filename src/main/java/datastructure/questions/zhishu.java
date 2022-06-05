package datastructure.questions;

import java.util.Scanner;

public class zhishu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number;
        number = in.nextInt();
        factor(number); //函数调用
        in.close(); //关流
    }

    static void factor(int number) {
        if (isPrime(number)) //首先进行判断是否为素数，如果是就直接输出
        {
            System.out.print(number);
        }
        for (int i = 2; i <= number - 1; i++) {
            if (number % i == 0) {
                System.out.print(i + "\t");
                int num = number / i; //进行一次分解num就要变一次！
                if (isPrime(num)) { //判断是否为素数，是的话就直接输出这个数字
                    System.out.print(num);
                } else { //不是素数就继续分解
                    factor(number / i); //利用函数递归的思想
                }
                // return ;
                break; //分解完了就退出
            }
        }
    }

    //判断是否为素数的函数
    static Boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
