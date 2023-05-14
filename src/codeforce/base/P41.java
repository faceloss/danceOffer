package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class P41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        System.out.println(getResult(words));
    }

    public static String getResult(String[] words) {
        // 对每个单词字母重新按字典序排序
        words = Arrays.stream(words)
                .map(word -> {
                    char[] charArr = word.toCharArray();
                    Arrays.sort(charArr);
                    return new String(charArr);
                })
                .toArray(String[]::new);

        // 统计每个单词出现的次数，并按次数降序排列
        // 次数相同，按单词长度升序排列
        // 次数和单词长度均相同，按字典升序排列
        HashMap<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        Arrays.sort(words, (a, b) -> {
            if (!count.get(a).equals(count.get(b))) {
                return count.get(b) - count.get(a);
            } else if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return a.compareTo(b);
            }
        });

        // 拼接结果字符串
        StringJoiner sj = new StringJoiner(" ", "", "");
        for (String word : words) {
            sj.add(word);
        }
        return sj.toString();
    }
}
