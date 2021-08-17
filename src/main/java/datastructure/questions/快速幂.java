package datastructure.questions;

/**
 *
 * (a + b) % p = (a % p + b % p) % p  （1）
 * (a - b) % p = (a % p - b % p ) % p （2）
 * (a * b) % p = (a % p * b % p) % p  （3）
 * a ^ b % p = ((a % p)^b) % p        （4）  ###
 *
 *
 */
public class 快速幂 {
    public static void main(String[] args) {
        // 2^5%5
        final long value = getValue(2, 5, 5);
        System.out.println(value);
    }

//    a^100001%30000230
    public static long getValue(long dishu,long zhishu,long yushu){
        dishu %= yushu;
        long res = 1;
        for (; zhishu != 0; zhishu /= 2) {
            if (zhishu % 2 == 1) {
                // 奇数    拆出一个底数 使指数为偶数
                res = res * dishu % yushu;
            }
            dishu = dishu * dishu % yushu;
        }
        return res;
    }
}
