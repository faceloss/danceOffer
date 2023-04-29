package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P45 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cacheCost = Integer.parseInt(scanner.nextLine()); // 缓存一个报告金币数M
        Integer[] fileIds = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new); // 文件标识序列
        Integer[] fileSizes = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new); // 文件大小序列

        System.out.println(getMinCost(cacheCost, fileIds, fileSizes));
    }

    public static int getMinCost(int cacheCost, Integer[] fileIds, Integer[] fileSizes) {
        // count用于保存每个文件出现的次数
        HashMap<Integer, Integer> count = new HashMap<>();
        // size用于保存文件的大小，即扫描成本
        HashMap<Integer, Integer> size = new HashMap<>();

        for (int i = 0; i < fileIds.length; i++) {
            Integer fileId = fileIds[i];
            count.put(fileId, count.getOrDefault(fileId, 0) + 1);
            size.putIfAbsent(fileId, fileSizes[i]);
        }

        int minCost = 0;
        for (Integer fileId : count.keySet()) {
            // 选择每次都重新扫描的成本和扫描一次+缓存的成本中最小的
            minCost += Math.min(count.get(fileId) * size.get(fileId), size.get(fileId) + cacheCost);
        }

        return minCost;
    }
}

