package codeforce.advance;

import java.util.ArrayList; // 导入 ArrayList 类
import java.util.Scanner; // 导入 Scanner 类
import java.util.StringJoiner; // 导入 StringJoiner 类

public class P10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象，用于读取用户输入

        int employeeNum = scanner.nextInt(); // 读取用户输入的员工数量

        int[] randomNumbers = new int[employeeNum]; // 创建一个长度为员工数量的整型数组
        for (int i = 0; i < employeeNum; i++) { // 循环读取用户输入的每个员工的随机数
            randomNumbers[i] = scanner.nextInt();
        }

        ArrayList<Integer> bonusList = new ArrayList<>(); // 创建一个 ArrayList 对象，用于存储每个员工的奖金金额

        outer: // 标签，用于跳出多层循环
        for (int i = 0; i < employeeNum; i++) { // 循环每个员工
            for (int j = i + 1; j < employeeNum; j++) { // 循环比当前员工编号大的员工
                if (randomNumbers[j] > randomNumbers[i]) { // 如果比当前员工的随机数大
                    bonusList.add((j - i) * (randomNumbers[j] - randomNumbers[i])); // 计算奖金金额并添加到 ArrayList 中
                    continue outer; // 跳出外层循环，继续处理下一个员工
                }
            }
            bonusList.add(randomNumbers[i]); // 如果没有比当前员工的随机数大的员工，则只能得到自己的随机数对应的奖金金额
        }

        StringJoiner sj = new StringJoiner(" "); // 创建一个 StringJoiner 对象，用于拼接字符串
        for (Integer bonus : bonusList) { // 循环 ArrayList 中的每个奖金金额
            sj.add(bonus + ""); // 将奖金金额转换为字符串并添加到 StringJoiner 中
        }

        System.out.println(sj.toString()); // 输出拼接好的字符串
    }
}
