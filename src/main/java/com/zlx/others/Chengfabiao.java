package com.zlx.others;

import com.zlx.GOF.methodfadctory2.User;

public class Chengfabiao {
    public static void main(String[] args) {
        for (int i = 9; i >= 0; i--) {
            for (int j = 1;j <= i;j++){
                System.out.print(i +"*"+j +"="+ i*j+"\t");
            }
            System.out.println();
        }
    }
}
