package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class P29 {
    public static void main(String[] args) {
        // 处理输入
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // 输入m
        Vector<Integer> nums1 = new Vector<>(); // 定义向量nums1
        Map<Integer, Integer> nums1_count = new HashMap<>(); // 定义映射nums1_count
        for (int i = 0; i < m; i++) { // 循环m次
            int x = sc.nextInt(); // 输入x
            nums1.add(x); // 将x添加到nums1中
            nums1_count.put(x, nums1_count.getOrDefault(x, 0) + 1); // 记录x出现的次数
        }

        int n = sc.nextInt(); // 输入n
        Vector<Integer> nums2 = new Vector<>(); // 定义向量nums2
        Map<Integer, Integer> nums2_count = new HashMap<>(); // 定义映射nums2_count
        for (int i = 0; i < n; i++) { // 循环n次
            int x = sc.nextInt(); // 输入x
            nums2.add(x); // 将x添加到nums2中
            nums2_count.put(x, nums2_count.getOrDefault(x, 0) + 1); // 记录x出现的次数
        }

        // 计算二元组个数
        long result = 0; // 定义结果变量result
        for (Map.Entry<Integer, Integer> entry : nums1_count.entrySet()) { // 循环nums1_count中的元素
            if (nums2_count.containsKey(entry.getKey())) { // 如果nums2_count中也有该元素
                result += (long)entry.getValue() * nums2_count.get(entry.getKey()); // 计算二元组个数并添加到result中
            }
        }

        // 输出结果
        System.out.println(result); // 输出result并换行
    }
}

