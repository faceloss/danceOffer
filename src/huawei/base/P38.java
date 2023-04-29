package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/



import java.util.Scanner;
import java.util.HashSet;

class P38 {
    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split(" ");

        // 将所有字符串放入哈希集合
        HashSet<String> wordSet = new HashSet<>();
        for (String input : inputs) {
            wordSet.add(input);
        }
        String password = "";

        // 真正的密码
        String password1 = "";

        // 按顺序检查每一个词
        for (String input : inputs) {
            // 条件1：检查这个词所有以索引0开头的子串在数组中是否都有
            boolean hasAllPrefixes = true;
            for (int i = 1; i < input.length(); i++) {
                // 以索引0开头的子串
                String prefix = input.substring(0, i);
                if (!wordSet.contains(prefix)) {
                    hasAllPrefixes = false;
                    break;
                }
            }

            if (hasAllPrefixes) {
                // 条件2：比较密码长度
                if (input.length() > password.length())
                    password1 = input;
                // 条件3：比较密码字典排序
                if (input.length() == password.length() && input.compareTo(password) > 0) {
                    password1 = input;
                }
            }
        }

        System.out.println(password);
    }
}


