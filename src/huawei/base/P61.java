package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:20
 **/

import java.util.ArrayList; // 导入ArrayList类
import java.util.HashMap; // 导入HashMap类
import java.util.Scanner; // 导入Scanner类

public class P61 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        String input = scanner.next(); // 获取输入的字符串
        System.out.println(getPalindrome(input)); // 输出回文串
    }

    private static String getPalindrome(String input) {
        HashMap<Character, Integer> charCount = new HashMap<>(); // 创建HashMap对象charCount，用于统计各字母个数

        // 统计各字母个数
        for (int i = 0; i < input.length(); i++) { // 遍历字符串input的每个字符
            char c = input.charAt(i); // 获取当前字符
            charCount.put(c, charCount.getOrDefault(c, 0) + 1); // 将当前字符加入charCount中，如果已存在则将其对应的值+1，否则将其对应的值设为1
        }

        ArrayList<Character> halfChars = new ArrayList<>(); // 创建ArrayList对象halfChars，用于存储回文串左边部分的字符
        String midChar = ""; // 创建字符串对象midChar，用于存储回文串中间的单字母

        for (char c : charCount.keySet()) { // 遍历charCount中的每个键（即每个不同的字符）
            int count = charCount.get(c); // 获取当前字符的出现次数

            // 如果字母数量大于等于2，则可以成对出现，
            if (count >= 2) { // 如果当前字符出现次数大于等于2
                for (int i = 0; i < count / 2; i++) { // 将其加入halfChars中count/2次（因为每个字符要成对出现）
                    halfChars.add(c); // 将当前字符加入halfChars中
                }
            }

            // 如果字母数量只有1个，或者字母数量大于2但是为奇数，则最后必然只剩单个字母可用，此时我们应该在这些无法成对的单字母中选择一个字典序最小的
            if (count % 2 != 0 && ("".equals(midChar) || midChar.compareTo(c + "") > 0)) { // 如果当前字符出现次数为奇数，且midChar为空或当前字符的字典序比midChar小
                midChar = c + ""; // 将当前字符赋值给midChar
            }
        }

        halfChars.sort((a, b) -> a - b); // 对halfChars进行升序排序
        StringBuilder leftHalf = new StringBuilder(); // 创建StringBuilder对象leftHalf，用于存储回文串左边部分的字符
        for (Character c : halfChars) { // 遍历halfChars中的每个字符
            leftHalf.append(c); // 将当前字符加入leftHalf中
        }

        return leftHalf + midChar + leftHalf.reverse(); // 返回回文串，由左边部分、中间单字母和右边部分组成
    }
}

