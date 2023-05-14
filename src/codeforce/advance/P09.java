package codeforce.advance;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

public class P09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt(); // 评分区间的长度
        scanner.nextLine(); // 读取换行符
        List<Integer> scoreList = Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList()); // 读取评分列表

        Deque<Integer> deque = new LinkedList<>(); // 定义单调队列
        for (int i = 0; i < scoreList.size(); i++) {
            if (i >= length) { // 如果滑动窗口的长度已经达到指定长度，则输出队首元素
                System.out.print(deque.peekFirst() + ",");
                if (scoreList.get(i - length) == deque.peekFirst()) { // 如果滑动窗口移动后，队首元素正好是滑动窗口移出的元素，则将其移出队列
                    deque.pollFirst();
                }
            }
            while (!deque.isEmpty() && deque.peekLast() > scoreList.get(i)) { // 当队列不为空且队列最后一个元素大于当前元素时，将队列最后一个元素移出队列，因为它不可能成为最小元素了
                deque.pollLast();
            }
            deque.addLast(scoreList.get(i)); // 将当前元素加入队列
        }
        System.out.print(deque.peekFirst()); // 输出最后一个滑动窗口的最小值
    }
}
