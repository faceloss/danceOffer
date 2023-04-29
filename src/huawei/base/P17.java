package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:00
 **/



import java.util.Arrays;
import java.util.Scanner;

public class P17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入的当前时间和目标完成时间
        int currentHour = scanner.nextInt();
        int currentMinute = scanner.nextInt();
        int targetHour = scanner.nextInt();
        int targetMinute = scanner.nextInt();

        // 读取输入的核酸点信息
        int n = scanner.nextInt();
        int[][] nucleicAcidPoints = new int[n][3];
        for (int i = 0; i < n; i++) {
            nucleicAcidPoints[i][0] = scanner.nextInt(); // 核酸点的ID值
            nucleicAcidPoints[i][1] = scanner.nextInt(); // 核酸检测点距离张三的距离
            nucleicAcidPoints[i][2] = scanner.nextInt(); // 核酸检测点当前检测的人数
        }

        // 调用getResult方法计算结果
        getResult(currentHour, currentMinute, targetHour, targetMinute, nucleicAcidPoints);
    }

    /**
     * 计算结果
     * @param currentHour 当前时间的小时数
     * @param currentMinute 当前时间的分钟数
     * @param targetHour 指定完成核算时间的小时数
     * @param targetMinute 指定完成核算时间的分钟数
     * @param nucleicAcidPoints [[核酸点的ID值, 核酸检测点距离张三的距离,核酸检测点当前检测的人数]]
     */
    public static void getResult(int currentHour, int currentMinute, int targetHour, int targetMinute, int[][] nucleicAcidPoints) {
        // 将时间转换成分钟数
        int start = currentHour * 60 + currentMinute;
        int end = targetHour * 60 + targetMinute;

        // 计算每个核酸点的到达时间、花费时间、收费金额
        int[][] result =
                Arrays.stream(nucleicAcidPoints)
                        .map(
                                nucleicAcidPoint -> {
                                    int id = nucleicAcidPoint[0];
                                    int distance = nucleicAcidPoint[1];
                                    int count = nucleicAcidPoint[2];

                                    int money = distance * 10; // 收费金额为距离*10
                                    int road = distance * 10; // 花在路上的时间为距离*10
                                    int arrived = start + road; // 到达核酸检测点的时间

                                    // 如果在8：00之前就赶到了，那么其实要等待到8:00才能排队，这里其实花费的时间应该包括等待的时间
                                    if (arrived < 8 * 60) {
                                        arrived = 8 * 60;
                                        road = arrived - start;
                                    }

                                    // 计算在不同时间段内排队的人数
                                    int[] timeRange1 = {start, arrived}; // 出发时间、到达时间
                                    int[] timeRange2 = {8 * 60, 10 * 60}; // 8:00-10:00
                                    int add1 = getIntersection(timeRange1, timeRange2); // 交集长度
                                    if (add1 != -1) {
                                        count += 2 * add1; // 每分钟净增2人
                                    }

                                    int[] timeRange3 = {10 * 60, 12 * 60}; // 10:00-12:00
                                    int min1 = getIntersection(timeRange1, timeRange3); // 交集长度
                                    if (min1 != -1) {
                                        count -= min1; // 每分钟净减1人
                                        count = Math.max(0, count); // 注意至多减到0
                                    }

                                    int[] timeRange4 = {12 * 60, 14 * 60}; // 12:00-14:00
                                    int add2 = getIntersection(timeRange1, timeRange4); // 交集长度
                                    if (add2 != -1) {
                                        count += 9 * add2; // 每分钟净增9人
                                    }

                                    int[] timeRange5 = {14 * 60, 20 * 60}; // 14:00-20:00
                                    int min2 = getIntersection(timeRange1, timeRange5); // 交集长度
                                    if (min2 != -1) {
                                        count -= min2; // 每分钟净减1人
                                        count = Math.max(0, count); // 注意至多减到0
                                    }

                                    return new int[] {id, count + road, money}; // 返回核酸点的ID、到达时间+花费时间、收费金额
                                })
                        // 过滤出规定结束时间end之前可达的核酸点
                        .filter(arr -> start + arr[1] <= end)
                        // 按照到达时间+花费时间、收费金额、ID的顺序排序
                        .sorted((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] != b[2] ? a[2] - b[2] : a[0] - b[0])
                        .toArray(int[][]::new);

        // 输出结果
        System.out.println(result.length);
        for (int[] arr : result) {
            System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        }
    }

    /**
     * 获取两个时间段的交集长度
     * @param timeRange1 时间段1
     * @param timeRange2 时间段2
     * @return 交集长度，如果没有交集返回-1
     */
    public static int getIntersection(int[] timeRange1, int[] timeRange2) {
        int start1 = timeRange1[0], end1 = timeRange1[1];
        int start2 = timeRange2[0], end2 = timeRange2[1];

        // 如果时间段1在时间段2之前，或者时间段1在时间段2之后，则没有交集
        if (start1 < start2) {
            if (start2 >= end1) return -1;
            else return Math.min(end1, end2) - start2;
        } else if (start1 > start2) {
            if (start1 >= end2) return -1;
            else return Math.min(end1, end2) - start1;
        } else {
            return Math.min(end1, end2) - start1;
        }
    }
}



