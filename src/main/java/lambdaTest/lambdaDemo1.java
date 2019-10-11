package lambdaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//集合遍历

public class lambdaDemo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,142,42,35,4667,235,346);

        list.forEach(System.out::println);

        System.out.println();

        list.forEach(ele->{
            if (ele%2 ==0){
                System.out.print(ele+" ");
            }
        });

        System.out.println();
        list.sort((w,e)->w.intValue()-e.intValue());
        list.forEach(System.out::println);

        Collections.sort(list);
        list.forEach(ele ->{
            System.out.print(ele+" ");
        });
        for (Integer i:
             list) {
            System.out.print(i+" ");
        }

        System.out.println(list);
    }
}
