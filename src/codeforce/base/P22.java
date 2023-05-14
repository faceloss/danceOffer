package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:00
 **/


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // 读取输入字符串
        Map<Character, Integer> charCount = new HashMap<>(); // 创建一个字符计数器

        // 统计字符串中每个字符出现的次数
        for (int i = 0; i < input.length(); i++) {
            char key = input.charAt(i);
            charCount.put(key, charCount.getOrDefault(key, 0) + 1);
        }

        // 防止输入没有WASD这四个字符
        charCount.put('W', charCount.getOrDefault('W', 0));
        charCount.put('A', charCount.getOrDefault('A', 0));
        charCount.put('S', charCount.getOrDefault('S', 0));
        charCount.put('D', charCount.getOrDefault('D', 0));

        // 如果WASD四个字符的出现次数相等，那么输出0并结束程序
        if (charCount.get('W').equals(charCount.get('A')) && charCount.get('W').equals(charCount.get('S')) && charCount.get('W').equals(charCount.get('D'))) {
            System.out.println(0);
            return;
        }

        int left = 0, right = 0, res = input.length(), maxCharNum = 0, freeCharNum = 0;
        charCount.put(input.charAt(0), charCount.get(input.charAt(0)) - 1);

        // 开始滑动窗口算法
        while (true) {
            // 计算窗口中出现次数最多的字符的出现次数
            maxCharNum = Math.max(Math.max(Math.max(charCount.get('W'), charCount.get('A')), charCount.get('S')), charCount.get('D'));
            int length = right - left + 1;
            // 计算窗口中除了出现次数最多的字符外，其余字符的出现次数之和
            freeCharNum = length - ((maxCharNum - charCount.get('W')) + (maxCharNum - charCount.get('A')) + (maxCharNum - charCount.get('S')) + (maxCharNum - charCount.get('D')));

            // 如果窗口中除了出现次数最多的字符外，其余字符的出现次数之和是4的倍数，那么更新结果
            if (freeCharNum >= 0 && freeCharNum % 4 == 0) {
                if (length < res) {
                    res = length;
                }
                charCount.put(input.charAt(left), charCount.get(input.charAt(left)) + 1);
                left++;
            } else {
                right++;
                if (right >= input.length()) {
                    break;
                }
                charCount.put(input.charAt(right), charCount.get(input.charAt(right)) - 1);
            }
            if (right >= input.length()) {
                break;
            }
        }
        System.out.println(res); // 输出结果
    }
}

