package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.HashMap;
import java.util.Scanner;

public class P50_SubstringCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String content = scanner.next(); // 输入的字符串
        String word = scanner.next(); // 要匹配的子串
        System.out.println(getSubstringCount(content, word));
    }

    public static int getSubstringCount(String content, String word) {
        // 如果content长度小于word，则直接返回0
        if (content.length() < word.length()) {
            return 0;
        }

        int count = 0; // 统计匹配的子串个数
        int total = word.length(); // 要匹配的子串中字符的总数

        // 统计要匹配的子串中各字符的数量
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // 初始滑动窗口内部字符遍历
        for (int i = 0; i < word.length(); i++) {
            char c = content.charAt(i);
            if (charCount.containsKey(c)) {
                if (charCount.get(c) > 0) {
                    total--;
                }
                charCount.put(c, charCount.get(c) - 1);
            }
        }

        if (total == 0) {
            count++;
        }

        // 滑动窗口左指针的移动范围为 0~maxI
        int maxI = content.length() - word.length();
        for (int i = 1; i <= maxI; i++) {
            char removeC = content.charAt(i - 1);
            char addC = content.charAt(i + word.length() - 1);

            if (charCount.containsKey(removeC)) {
                if (charCount.get(removeC) >= 0) {
                    total++;
                }
                charCount.put(removeC, charCount.get(removeC) + 1);
            }

            if (charCount.containsKey(addC)) {
                if (charCount.get(addC) > 0) {
                    total--;
                }
                charCount.put(addC, charCount.get(addC) - 1);
            }

            if (total == 0) {
                count++;
            }
        }
        return count;
    }
}

