package com.zlx.JUC;

import java.util.*;

public class MapTest {


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("aa","bb");
        map.put("cc","qq");
        map.put("dd","ff");

        //只遍历 key 或 value
        for (String s:map.keySet()){
            System.out.println(s);
        }
        for (String s:map.values()){
            System.out.println(s);
        }

        //通过entry遍历
        for (Map.Entry<String,String> entry:map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }

        //通过迭代器 Iterator
        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }

        //最简单的方式 效率较低
        for (String key:map.keySet()){
            String value = map.get(key);
            System.out.println(key+":"+value);
        }


        List<String> list = new ArrayList<String>(Arrays.asList("ff","tt"));
        Collections.addAll(list,"1","2","3","dfsfsf");
        Arrays.asList(list,"ll","qq");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = (String)iterator.next();
            if (s.equals("3")){
                iterator.remove();
            }
            System.out.println(s);
        }
        System.out.println(list);

    }
}
