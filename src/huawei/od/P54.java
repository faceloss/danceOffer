package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Scanner;

public class P54 {
    public static void main(String[] args) {
        // 读取输入
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        String[] inputList = inputStr.split(" ");

        // 将字符串数组转换为整型数组
        int[] cars = new int [inputList.length];
        for (int i = 0; i < inputList.length; i++) {
            cars[i] = Integer.parseInt(inputList[i]);
        }

        // 读取滑动窗口大小
        String windowSizeStr = scanner.nextLine();
        int windowSize = Integer.valueOf(windowSizeStr);

        // 初始化滑动窗口
        int[] carCount = new int[3];
        for (int i = 0; i < windowSize; i++) {
            carCount[cars[i]] += 1;
        }

        // 滑动窗口向前滑动
        int maxRes = Math.max(Math.max(carCount[0], carCount[1]), carCount[2]);
        for (int i = windowSize; i < cars.length; i++) {
            carCount[cars[i]] += 1;
            carCount[cars[i - windowSize]] -= 1;
            maxRes = Math.max(maxRes, Math.max(Math.max(carCount[0], carCount[1]), carCount[2]));
        }

        // 输出结果
        System.out.println(maxRes);
    }
}
