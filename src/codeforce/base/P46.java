package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class P46 {
    public static void main(String[] args) {
        // 读取输入
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(); // 包裹数量
        int n = scanner.nextInt(); // 检查站数量
        scanner.nextLine(); // 读取换行符

        // 读取包裹信息
        String[][] packageInfo = new String[m][];
        for (int i = 0; i < m; i++) {
            packageInfo[i] = scanner.nextLine().split(" "); // 读取一行包裹信息
        }

        // 读取检查站信息
        String[][] checkpointInfo = new String[n][];
        for (int i = 0; i < n; i++) {
            checkpointInfo[i] = scanner.nextLine().split(" "); // 读取一行检查站信息
        }

        // 构建包裹信息和检查站信息的哈希表
        HashMap<String, HashSet<String>> packageMap = new HashMap<>();
        HashMap<String, HashSet<String>> checkpointMap = new HashMap<>();

        for (String[] singlePackage : packageInfo) {
            // 合并起点终点作为key
            packageMap.putIfAbsent(singlePackage[1] + "-" + singlePackage[2], new HashSet<>());
            packageMap.get(singlePackage[1] + "-" + singlePackage[2]).add(singlePackage[0]); // 将包裹号添加到对应的起点终点的集合中
        }

        for (String[] singleCheckpoint : checkpointInfo) {
            checkpointMap.putIfAbsent(singleCheckpoint[0] + "-" + singleCheckpoint[1], new HashSet<>());
            checkpointMap.get(singleCheckpoint[0] + "-" + singleCheckpoint[1]).addAll(Arrays.asList(Arrays.copyOfRange(singleCheckpoint, 2, singleCheckpoint.length))); // 将被禁止的包裹号添加到对应的起点终点的集合中
        }

        // 查找被禁止的包裹
        ArrayList<String> result = new ArrayList<>();
        for (String key : packageMap.keySet()) {
            HashSet<String> packages = packageMap.get(key); // 获取起点终点对应的包裹号集合
            HashSet<String> bannedPackages = checkpointMap.get(key); // 获取起点终点对应的被禁止的包裹号集合

            if (bannedPackages == null) { // 如果该起点终点没有被禁止的包裹，则继续下一轮循环
                continue;
            }

            for (String singlePackage : packages) { // 遍历该起点终点对应的所有包裹号
                if (bannedPackages.contains(singlePackage)) { // 如果该包裹号被禁止，则将其添加到结果集中
                    result.add(singlePackage);
                }
            }
        }

        // 输出结果
        if (result.size() == 0) {
            System.out.println("none"); // 如果结果集为空，则输出"none"
        } else {
            // 按包裹名排序
            result.sort((a, b) -> Integer.parseInt(a.substring(7)) - Integer.parseInt(b.substring(7))); // 按照包裹号的数字部分排序

            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i)); // 输出结果集中的每个包裹号
                if (i != result.size() - 1) {
                    System.out.print(" "); // 如果不是最后一个包裹号，则输出一个空格
                }
            }
            System.out.println(); // 输出换行符
        }
    }
}

