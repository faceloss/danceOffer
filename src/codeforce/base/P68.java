package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:23
 **/

import java.util.*;

public class P68 {
    // 判断两个字符串是否由相同字符组成
    public static boolean check(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        Arrays.sort(aArray); // 对a中的字符进行升序排序
        Arrays.sort(bArray); // 对b中的字符进行升序排序

        for (int i = 0; i < aArray.length; i++) {
            if (aArray[i] != bArray[i]) { // 如果a和b中的字符不同，则返回false
                return false;
            }
        }
        // 如果a和b中的字符全部都相同，返回true
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入处理，获取单词数量
        String n_str = scanner.nextLine();
        int N = Integer.parseInt(n_str);

        // 存储输入的所有单词
        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String temp_str = scanner.nextLine();
            words.add(temp_str); // 将输入的单词加入words列表
        }

        // 获取目标单词
        String target_word = scanner.nextLine();

        // 存储符合条件的单词
        List<String> result = new ArrayList<>();
        for (String word : words) {
            // 比较两个字符串是否由相同字符组成
            if (word.length() == target_word.length() && check(target_word, word)) {
                result.add(word); // 将符合条件的单词加入result向量
            }
        }

        // 对结果进行排序
        Collections.sort(result);

        // 输出结果
        if (result.size() == 0) {
            System.out.println("null");
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i != result.size() - 1) {
                    System.out.print(" ");
                }
            }
        }
    }
}
