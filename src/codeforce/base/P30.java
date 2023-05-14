package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/

import java.util.*;

public class P30 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);  // 创建Scanner对象，用于读取用户输入
        int n = in.nextInt();  // 读取用户输入的积木总数
        Map<Integer, List<Integer>> blockMap = new HashMap<>();  // 创建一个HashMap对象，用于记录每个数字对应的积木位置
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();  // 读取用户输入的积木上的数字
            List<Integer> blockPos = blockMap.getOrDefault(num, new ArrayList<>());  // 获取当前数字对应的积木位置列表，如果不存在则创建一个新的空列表
            blockPos.add(i);  // 将当前积木的位置添加到列表中
            blockMap.put(num, blockPos);  // 将更新后的列表重新放回HashMap中
        }

        int maxDistance = -1;  // 初始化相同数字的积木的位置最远距离为-1
        for (List<Integer> blockPos : blockMap.values()) {  // 遍历HashMap中所有的积木位置列表
            if (blockPos.size() > 1) {  // 如果当前数字对应的积木数量大于1，说明存在相同数字的积木
                int distance = Collections.max(blockPos) - Collections.min(blockPos);  // 计算相同数字的积木的位置最远距离
                maxDistance = Math.max(maxDistance, distance);  // 更新相同数字的积木的位置最远距离
            }
        }
        System.out.println(maxDistance);  // 输出相同数字的积木的位置最远距离
    }
}

