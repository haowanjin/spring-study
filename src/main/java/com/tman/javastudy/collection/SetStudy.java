package com.tman.javastudy.collection;

import java.util.*;

/**/
public class SetStudy {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            Set<String> set = new HashSet<>();
           while (n>0){
                set.add(in.next());
                n--;
            }
            for (String str : set) {
                System.out.println(str);
            }
        }
    }

    public static int getResult(String input){
        Map<Character,Object> map = new HashMap<>();
        for(int i=0;i<input.length();i++){
            char c = input.charAt(i);
            if(0<c &&c<127){
                map.put(c,new Object());
            }
        }
        return map.keySet().size();
    }
}
