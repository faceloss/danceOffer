package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:20
 **/

import java.util.Scanner;
import java.util.*;

class P65 {
    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        int numOfVersions = scanner.nextInt();  // 版本数量

        int[][] matrix = new int[numOfVersions][numOfVersions];   // 版本信息矩阵
        for(int i=0; i<numOfVersions; i++){
            for(int j=0; j<numOfVersions; j++){
                matrix[i][j] = scanner.nextInt();    // 将每行版本信息存储在 matrix 中
            }
        }

        //记录已经遍历过的版本号
        Set<Integer> visitedVersions = new HashSet<>();
        int res = 0;   // 最大关联版本数量
        for(int i=0; i<numOfVersions; i++){
            if(!visitedVersions.contains(i)){   // 若当前版本已经遍历过，则跳过
                Set<Integer> currentVersionSet = new HashSet<>();   // 当前版本集合
                check(currentVersionSet, i, matrix);   // 深度优先搜索找到以 i 版本为起点的关联版本
                res = Math.max(res, currentVersionSet.size());   // 更新最大关联版本数量
                visitedVersions.addAll(currentVersionSet);   // 将当前版本集合中的所有版本加入 visitedVersions
            }
        }

        System.out.println(res);   // 输出最大关联版本数量
    }

    public static void check(Set<Integer> currentVersionSet, int n, int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            if(currentVersionSet.contains(i)){    // 若该节点已被访问过，则跳过
                continue;
            }
            if(n != i && matrix[n][i] == 1){   // 如果当前版本与 n 版本相连，则将其加入到版本集合中
                currentVersionSet.add(i);
                check(currentVersionSet, i, matrix);   // 继续递归查找以 i 节点为起点的关联版本
            }
        }
    }
}


