package huawei.advance;

import java.util.*;

public class P35 {
    
    public static Set<Integer> split(String input_str) {
        Set<Integer> v = new HashSet<>();
        
        while (input_str.indexOf(",") != -1) {
            int found = input_str.indexOf(",");
            v.add(Integer.parseInt(input_str.substring(0, found)));
            input_str = input_str.substring(found + 1);
        }    
        v.add(Integer.parseInt(input_str));
        return v;
    }
    
    public static int distinct_length(String s){
        Set<Character> set_temp = new HashSet<>();
        for (char c : s.toCharArray()){
            set_temp.add(c);
        }
 
        return set_temp.size();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string1 = sc.next();
        String string2 = sc.next();
        
        String temp = "";
        List<String> res_list = new ArrayList<>();  
        int max_len = 0;
        int diffLen = distinct_length(string2);  
        for(int i=0; i<string1.length(); i++){
            char c = string1.charAt(i);
            //有效字母
            if(c >= 'g' && c <= 'z'){  
                temp += c;
                if(i != string1.length() - 1) continue;    
            }
            if(!temp.equals("")){
                int len = distinct_length(temp);   
                //数量必须要小于等于string2的不同字母数量 
                if(len <= diffLen){     
                    if(len > max_len){
                        res_list.clear();       
                        max_len = Math.max(max_len, len);  
                    }
                    res_list.add(temp);
                }
                temp  = "";    
            }
        }
 
        Collections.sort(res_list);
        if (res_list.size() == 0) {
            System.out.println("Not Found");
        } else{
            System.out.println(res_list.get(res_list.size()-1));
        }
 
        sc.close();
    }
}
