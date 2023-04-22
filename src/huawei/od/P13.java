package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 21:59
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象，用于从控制台读取输入
        String password = scanner.nextLine(); // 读取第一行输入，即密码
        String[] boxes = scanner.nextLine().split(" "); // 读取第二行输入，即所有盒子的字符串，使用空格分隔并转为数组
        int result = -1; // 初始化结果为-1，表示未找到

        // 遍历所有盒子
        for (int i = 0; i < boxes.length; i++) {
            String lowerCase = boxes[i].toLowerCase(); // 将盒子字符串转为小写字母
            List<Character> letters = new ArrayList<>(); // 创建一个字符列表，用于存储盒子中的字母

            // 遍历盒子中的每个字符
            for (char c : lowerCase.toCharArray()) {
                if (c >= 'a' && c <= 'z') { // 如果是小写字母
                    letters.add(c); // 将其加入字符列表
                }
            }

            letters = letters.stream().sorted().collect(Collectors.toList()); // 将字符列表按字典序排序
            StringBuilder builder = new StringBuilder(); // 创建一个StringBuilder对象，用于拼接排好序的字母

            // 遍历排好序的字母列表
            for (Character c : letters) {
                builder.append(c); // 将每个字母加入StringBuilder中
            }

            if (builder.toString().equals(password)) { // 如果拼接后的字符串与密码相等
                result = i + 1; // 将结果设为当前盒子的编号
                break; // 跳出循环
            }
        }

        System.out.println(result); // 输出结果
    }
}

