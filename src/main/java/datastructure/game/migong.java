package datastructure.game;

public class migong {
    public static void main(String[] args) {
        int i = 1;
        int j = 1;
        int[][] map = createMap(i,j);

    }
    public static int[][] createMap(int x,int y){
        int[][] array = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0;  j< 5; j++) {
                array[i][j] = 0;
            }
        }
        for (int i = 0; i < 5; i++) {
            array[0][i] = 1;
            array[4][i] = 1;
            array[i][0] = 1;
            array[i][4] = 1;
            array[2][2] = 1;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }

        while (x != 3 || y != 3){
            toUp(array,x,y);
            toRight(array,x,y);
            toDown(array,x,y);
            toLeft(array,x,y);
        }

        return array;
    }

    public static void toUp(int[][] arr,int i,int j){
        if (arr[i][j] != 1){
            arr[i][j] = 2;
            j++;
        }
    }

    public static void toRight(int[][] arr,int i,int j){
        if (arr[i][j] != 1){
            arr[i][j] = 2;
            i++;
        }
    }

    public static void toDown(int[][] arr,int i,int j){
        if (arr[i][j] != 1){
            arr[i][j] = 2;
            j--;
        }
    }

    public static void toLeft(int[][] arr,int i,int j){
        if (arr[i][j] != 1){
            arr[i][j] = 2;
            i--;
        }
    }
}
