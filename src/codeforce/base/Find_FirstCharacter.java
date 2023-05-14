package codeforce.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: danceOffer
 * @description: 字符串找到出现1次最左的位置
 * @author: mobing_yin
 * @create: 2023-04-22 17:03
 **/

public class Find_FirstCharacter {
    public static void main(String[] args) {
        String str = "abababe";
        System.out.println(findIndexNew(str));
    }
    public static int findIndex(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() ; i++) {
            char key = s.charAt(i);
            if(map.containsKey(key)){
                map.put(key, map.get(key)+1);
            }else {
                map.put(key, 1);
            }
        }
        List<Character> base = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                base.add(entry.getKey());
            }
        }
        for (int i = 0; i < s.length() ; i++) {
            if(base.contains(s.charAt(i))){
                return i;
            }
        }
        return -1;
     }
    public  static int findIndexNew(String s) {
        int[][] or = new int[26][2]; //一开始优化的时候用的是一维数组，面试结束后想到二维数组
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            or[c-'a'][0]++;
            or[c-'a'][1]=i;
        }
        int len = s.length();
        int res = len;
        for (int[] arr : or) {
            if (arr[0] == 1) {
                //最小索引
                return arr[1];
//                res = Math.min(arr[1], res);
            }
        }
        return -1;
    }
}
