package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class P32 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine(); // 定义输入字符串

        // 空格分割
        ArrayList<String> word_list = new ArrayList<>(); // 定义动态数组容器
        while (input_str.contains(" ")) { // 如果输入字符串中还有空格
            int found = input_str.indexOf(" "); // 找到空格的位置
            word_list.add(input_str.substring(0, found)); // 将空格之前的字符串放入动态数组容器中
            input_str = input_str.substring(found + 1); // 更新输入字符串，去掉已经放入容器中的字符串和空格
        }
        word_list.add(input_str); // 最后一个字符串没有空格，需要单独放入容器中

        // 将所有字符串放入哈希集合
        HashSet<String> word_set = new HashSet<>(); // 定义集合容器
        for (String word : word_list) { // 遍历动态数组容器中的每一个字符串
            word_set.add(word); // 将字符串放入集合容器中，集合会自动去重
        }

        // 真正的密码
        String true_password = ""; // 定义真正的密码为空字符串

        // 按顺序检查每一个词
        for (String word : word_list) { // 遍历动态数组容器中的每一个字符串
            // 条件1：检查这个词所有以索引0开头的子串在数组中是否都有
            boolean flag = true; // 定义标志位为真
            for (int i = 1; i < word.length(); i++) { // 遍历字符串中的每一个字符
                // 以索引0开头的子串
                String sub_str = word.substring(0, i); // 取出以索引0开头的子串
                if (!word_set.contains(sub_str)) { // 如果集合容器中没有这个子串
                    flag = false; // 将标志位设为假
                    break; // 退出循环
                }
            }

            if (flag) { // 如果标志位为真
                // 条件2：比较密码长度
                if (word.length() > true_password.length()) // 如果当前字符串的长度大于真正的密码长度
                    true_password = word; // 将当前字符串设为真正的密码
                // 条件3：比较密码字典排序
                if (word.length() == true_password.length() && word.compareTo(true_password) > 0) { // 如果当前字符串的长度等于真正的密码长度，并且当前字符串字典序大于真正的密码
                    true_password = word; // 将当前字符串设为真正的密码
                }
            }
        }

        System.out.println(true_password); // 输出真正的密码
    }
}

