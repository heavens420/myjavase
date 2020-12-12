package datastructure.game;

import lombok.Data;

/**
 * 0 为路
 * 1 为墙
 * 2 通路
 * 3 走过
 */
@Data
public class migong {
    private volatile static int x = 1;
    private volatile static int y = 1;

    public static void main(String[] args) {
//        int x = 1;
//        int y = 1;
        int[][] map = createMap(x, y);
        System.out.println("-------------------");
        setWay(map, x, y);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

    }

    public static int[][] createMap(int x, int y) {
        int[][] array = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = 0;
            }
        }

        //设置墙
        for (int i = 0; i < 5; i++) {
            array[0][i] = 1;
            array[4][i] = 1;
            array[i][0] = 1;
            array[i][4] = 1;
            array[2][2] = 1;
            array[3][2] = 1;
        }
        //打印迷宫
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        return array;
    }


    public static boolean setWay(int[][] map, int x, int y) {
        if (map[3][3] == 2) {
            return true;
        } else {
            if ((map[x][y] == 0)) {
                //假设该点可以走通
                map[x][y] = 2;
                if (setWay(map, x + 1, y)) {
                    // 下
                    return true;
                } else if (setWay(map, x, y + 1)) {
                    // 右
                    return true;
                } else if (setWay(map, x - 1, y)) {
                    // 上
                    return true;
                } else if (setWay(map, x, y - 1)) {
                    // 左
                    return true;
                } else {
                    map[x][y] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
