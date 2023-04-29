package huawei.advance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class P25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int taskNum = scanner.nextInt(); // 任务数量
        int relationsNum = scanner.nextInt(); // 任务之间的依赖关系数量

        int[][] relations = new int[relationsNum][2]; // 依赖关系数组
        for (int i = 0; i < relationsNum; i++) {
            relations[i][0] = scanner.nextInt(); // 前置任务
            relations[i][1] = scanner.nextInt(); // 后继任务
        }

        System.out.println(getResult(relations, taskNum)); // 输出最短建站时间
    }

    public static int getResult(int[][] relations, int taskNum) {
        HashMap<Integer, ArrayList<Integer>> nextTasks = new HashMap<>(); // 存储每个任务的后继任务
        int[] inDegrees = new int[taskNum]; // 存储每个任务的入度

        for (int[] relation : relations) {
            int preTask = relation[0];
            int nextTask = relation[1];

            nextTasks.putIfAbsent(preTask, new ArrayList<>()); // 如果preTask没有后继任务，则新建一个空列表
            nextTasks.get(preTask).add(nextTask); // 将nextTask添加到preTask的后继任务列表中
            inDegrees[nextTask]++; // nextTask的入度加1
        }

        LinkedList<Integer[]> queue = new LinkedList<>(); // 存储入度为0的任务
        int shortestTime = 1; // 最短建站时间

        for (int i = 0; i < taskNum; i++) {
            if (inDegrees[i] == 0) {
                queue.add(new Integer[]{i, shortestTime}); // 将入度为0的任务添加到队列中
            }
        }

        while (queue.size() > 0) {
            Integer[] tmp = queue.removeFirst(); // 取出队首任务
            int task = tmp[0]; // 当前任务
            int time = tmp[1]; // 当前任务所处建站时间

            if (nextTasks.containsKey(task) && nextTasks.get(task).size() > 0) {
                for (Integer nextTask : nextTasks.get(task)) {
                    // 该顶点被删除，则其后继点的入度值--，若--后入度为0，则将成为新的出队点
                    if (--inDegrees[nextTask] == 0) {
                        shortestTime = time + 1; // 建站时间加1
                        queue.add(new Integer[]{nextTask, shortestTime}); // 将入度为0的任务添加到队列中
                    }
                }
            }
        }

        return shortestTime; // 返回最短建站时间
    }
}

