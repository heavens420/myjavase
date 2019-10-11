package datastructure;

public class xishuarray {
    // 0 表示 空
    // 1 表示黑子
    // 2 表示白子

    public static void main(String[] args) {
        int chess[][] = new int[11][11];
        chess[0][0] = 1;
        chess[3][3] = 2;
        chess[5][5] = 1;
        chess[3][4] = 2;
        chess[6][9] = 2;
        System.out.println("原始数组:");
        for (int[] row:
             chess) {
            for (int data :row){
                System.out.print(data+"\t");
            }
            System.out.println();
        }

        //获取棋盘中总共有多少个棋子
        int num = 0;
        for (int i=0;i<chess.length;i++){
            for (int j=0;j<chess.length;j++){
                if (chess[i][j] != 0){
                    num++;
                }
            }
        }
        //创建 稀疏数组
        int count = 0;
        int xishu[][] = new int[num+1][3];
        xishu[0][0] = 11;
        xishu[0][1] = 11;
        xishu[0][2] = num;
        for (int i=0;i<chess.length;i++){
            for (int j=0;j<chess.length;j++){
                if (chess[i][j] != 0){
                    count++;
                    xishu[count][0] = i;
                    xishu[count][1] =j;
                    xishu[count][2] = chess[i][j];
                }
            }
        }

        //遍历稀疏数组
        System.out.println("稀疏数组：");
        for (int i=0;i<=num;i++){
            System.out.print(xishu[i][0]+"\t"+xishu[i][1]+"\t"+xishu[i][2]+"\n");
        }

        System.out.println("------------------------------");
        for (int[] ints : xishu) {
            for (int i:
                 ints) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }

        System.out.println("------------------------");
        for (int i = 0; i < num+1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(xishu[i][j]+"\t");
            }
            System.out.println();
        }

        //将稀疏数组 转化为普通数组
        int chess2[][] = new int[xishu[0][0]][xishu[0][1]];
        for (int i = 1; i < xishu.length; i++) {//总共 num个棋子  遍历 num次 也就是 xishu数组的长度
            chess2[xishu[i][0]][xishu[i][1]] = xishu[i][2];
        }

        //遍历数组
        System.out.println("还原后的数组：");
        for (int[] ints:
             chess2) {
            for (int i:
                 ints) {
                System.out.print(i+"\t");
                }
            System.out.println();
             }
        }
}
