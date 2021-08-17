package datastructure.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringNumber {

    private  static Pattern  pattern = Pattern.compile("[A-Z]+");

    public static void main(String[] args) {
        test2();
    }

    public static void test2(){
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 3, 4, 5, 6));

        LinkedHashSet<Integer> set = new LinkedHashSet<>(arrayList);

        set.forEach(System.out::println);
    }

    public static void test1(){
        final Matcher matcher = pattern.matcher("sdfsfDFfdsEF");

        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}
