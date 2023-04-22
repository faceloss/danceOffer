package huawei.od;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @program: danceOffer
 * @description:
 * 题目描述
 * 任务编排服务负责对任务进行组合调度。
 * 参与编排的任务有两种类型，其中一种执行时长为taskA，另一种执行时长为taskB。
 * 任务一旦开始执行不能被打断，且任务可连续执行。
 * 服务每次可以编排num个任务。
 * 请编写一个方法，生成每次编排后的任务所有可能的总执行时长。
 * 输入描述
 * 第1行输入分别为第1种任务执行时长taskA，
 * 第2种任务执行时长taskB，
 * 这次要编排的任务个数num，以逗号分隔。
 * 注：每种任务的数量都大于本次可以编排的任务数量
 * 0 < taskA
 * 0 < taskB
 * 0 <= num <= 100000
 * 输出描述
 * 数组形式返回所有总执行时时长，需要按从小到大排列。
 * 用例
 * 输入:
 * 1,2,3
 * 输出:
 * [3, 4, 5, 6]
 * 说明:
 * 可以执行 3 次 taskA，得到结果 3: 执行 2次 taskA和 次 taskB，得到结果 4。以此类推，得到最终结果.
 * @author: mobing_yin
 * @create: 2023-04-22 21:50
 **/

public class P08_TotalExecutionTime_OfTheTask {
    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象，用于读取用户输入
        List<Integer> taskTimes = Arrays.stream(scanner.nextLine().split(",")) // 读取用户输入，将其转为数组
                .map(Integer::parseInt) // 将数组中的每个元素转为整数类型
                .collect(Collectors.toList()); // 将转换后的数组转为 List 集合

        int taskATime = taskTimes.get(0); // 获取任务 A 的处理时间
        int taskBTime = taskTimes.get(1); // 获取任务 B 的处理时间
        int num = taskTimes.get(2); // 获取总任务数

        // 用 TreeSet 存储结果，自动排序且去重
        Set<Integer> totalTimes = new TreeSet<>(); // 创建 TreeSet 对象，用于存储结果
        for (int i = 0; i <= num; i++) { // 遍历所有可能的情况
            int res = taskATime * (num - i) + i * taskBTime; // 计算总耗时
            totalTimes.add(res); // 将计算结果添加到 TreeSet 中
        }

        // 输出结果
        System.out.println(totalTimes); // 将 TreeSet 中的结果输出到控制台
    }
}

