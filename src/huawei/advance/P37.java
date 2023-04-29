package huawei.advance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P37 {
    public static ArrayList<String> resultList;

    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        String numString = scanner.nextLine();
        String blockString = scanner.nextLine();
        // 预设值
        HashMap<Character, String> numCharMap = new HashMap<Character, String>();
        numCharMap.put('0', "abc");
        numCharMap.put('1', "def");
        numCharMap.put('2', "ghi");
        numCharMap.put('3', "jkl");
        numCharMap.put('4', "mno");
        numCharMap.put('5', "pqr");
        numCharMap.put('6', "st");
        numCharMap.put('7', "uv");
        numCharMap.put('8', "wx");
        numCharMap.put('9', "yz");

        resultList = new ArrayList<String>();
        ArrayList<Character> tempList = new ArrayList<Character>();
        dfs(numString, tempList, 0, numCharMap);

        StringBuilder outputStringBuilder = new StringBuilder();
        for (String str : resultList) {
            // 过滤
            if (!check(str, blockString)) {
                outputStringBuilder.append(str).append(" ");
            }
        }

        System.out.println(outputStringBuilder.toString().trim());
    }

    // 判断字符是否全部包含
    public static boolean check(String string1, String string2) {
        Set<Character> set1 = new HashSet<Character>();
        for (int i = 0; i < string1.length(); i++) {
            set1.add(string1.charAt(i));
        }

        Set<Character> set2 = new HashSet<Character>();
        for (int i = 0; i < string2.length(); i++) {
            set2.add(string2.charAt(i));
        }

        for (Character singleChar : set2) {
            if (!set1.contains(singleChar)) {
                return false;
            }
        }

        return true;
    }

    // 递归求出所有可能的排列组合
    public static void dfs(String numString, ArrayList<Character> list, int index, HashMap<Character, String> numCharMap) {
        if (index == numString.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i));
            }
            resultList.add(stringBuilder.toString());
            return;
        }

        for (char singleChar : numCharMap.get(numString.charAt(index)).toCharArray()) {
            list.add(singleChar);
            dfs(numString, list, index + 1, numCharMap);
            list.remove(list.size() - 1);
        }
    }
}
