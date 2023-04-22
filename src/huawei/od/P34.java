package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/

import java.util.ArrayList;
import java.util.Scanner;

public class P34 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象

        String box = scanner.next(); // 读取输入的字符串
        int width = scanner.nextInt(); // 读取输入的整数

        getResult(box, width); // 调用getResult方法并传入参数
    }

    public static void getResult(String box, int width) { // 定义getResult方法
        ArrayList<ArrayList<Character>> matrix = new ArrayList<>(); // 创建二维ArrayList对象
        for (int i = 0; i < width; i++) { // 循环width次
            matrix.add(new ArrayList<>()); // 向matrix中添加一个空的ArrayList对象
        }

        boolean reverse = true; // 定义布尔变量reverse并初始化为true
        for (int i = 0; i < box.length(); i++) { // 循环box的长度次
            int index = i % width; // 计算当前字符应该放在第几列
            if (index == 0) { // 如果当前字符应该放在第一列
                reverse = !reverse; // 反转reverse的值
            }
            if (reverse) { // 如果reverse为true
                index = width - 1 - index; // 计算当前字符应该放在倒数第几列
            }
            matrix.get(index).add(box.charAt(i)); // 将当前字符添加到matrix中相应的ArrayList中
        }

        for (ArrayList<Character> list : matrix) { // 循环matrix中的每一个ArrayList
            StringBuilder stringBuilder = new StringBuilder(); // 创建StringBuilder对象
            for (Character character : list) { // 循环当前ArrayList中的每一个字符
                stringBuilder.append(character); // 将当前字符添加到stringBuilder中
            }
            System.out.println(stringBuilder); // 输出stringBuilder中的字符串
        }
    }
}

