package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P40 {
    public static int maxMachine = 0; // 最大机器数

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> logs = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList()); // 处理输入并转换为列表

        int[] plusScores = new int[logs.size()]; // 加分数组
        plusScores[0] = logs.get(0);

        int[] minusScores = new int[logs.size()]; // 减分数组
        minusScores[0] = 0;

        int[] results = new int[logs.size()]; // 结果数组
        results[0] = logs.get(0);

        for (int i = 1; i < logs.size(); i++) {
            plusScores[i] = Math.min(100, plusScores[i - 1] + logs.get(i)); // 计算加分
            minusScores[i] = minusScores[i - 1] + plusScores[i - 1]; // 计算减分
            results[i] = plusScores[i] - minusScores[i]; // 计算结果

            if (plusScores[i] >= 100) { // 如果加分达到100，跳出循环
                break;
            }
        }

        int maxScore = 0; // 最大得分
        for (int score : results) { // 找出最大得分
            if (score > maxScore) {
                maxScore = score;
            }
        }
        System.out.println(maxScore); // 输出最大得分
    }
}

