package datastructure.questions;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String result = "";
        String[] strings = scanner.nextLine().split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            if (!strings[i].trim().equals("")) {
                result += strings[i] + " ";
            }
        }
        System.out.println(result.trim());
    }
}
