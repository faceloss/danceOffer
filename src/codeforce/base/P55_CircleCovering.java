package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Scanner;

public class P55_CircleCovering {
    public static void main(String[] args) {
        //创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        //读取输入的整数n
        int n = scanner.nextInt();
        //初始化最小位置和最大位置
        double minPos = 0;
        double maxPos = Integer.MAX_VALUE;
        //创建二维数组centers用于存储圆心坐标
        double[][] centers = new double[n][2];
        //循环读取n个圆心坐标并计算最小位置和最大位置
        for (int i = 0; i < n; i++) {
            centers[i][0] = scanner.nextInt();
            centers[i][1] = scanner.nextInt();
            minPos = Math.min(minPos, Math.min(centers[i][0], centers[i][1]));
            maxPos = Math.max(maxPos, Math.max(centers[i][0], centers[i][1]));
        }
        //二分查找最小覆盖半径
        while (minPos < maxPos) {
            //计算中间位置
            double midPos = Math.ceil((minPos + maxPos) / 2);
            //计算当前位置下的总距离
            double distance = calDistance(midPos, centers);
            //计算左侧位置下的总距离
            double distanceLeft = calDistance(midPos - 0.5, centers);
            //计算右侧位置下的总距离
            double distanceRight = calDistance(midPos + 0.5, centers);
            //如果当前位置下的总距离最小，则输出并结束程序
            if (distance <= distanceLeft && distance <= distanceRight) {
                System.out.println((int) distance);
                return;
            }
            //如果左侧位置下的总距离更小，则更新最小位置
            if (distance < distanceLeft) {
                minPos = midPos + 0.5;
                continue;
            }
            //如果右侧位置下的总距离更小，则更新最大位置
            if (distance < distanceRight) {
                maxPos = midPos - 0.5;
            }
        }
        //如果没有找到最小覆盖半径，则输出0
        System.out.println(0);
    }

    //计算当前位置下的总距离
    public static double calDistance(double pos, double[][] centers) {
        double dis = 0;
        for (double[] center : centers) {
            //如果圆心在当前位置下方，则加上圆心到当前位置的距离
            if (center[1] < pos) {
                dis += pos - center[1];
                //如果圆心在当前位置上方，则加上当前位置到圆心的距离
            } else if (pos < center[0]) {
                dis += center[0] - pos;
            }
        }
        return dis;
    }
}

