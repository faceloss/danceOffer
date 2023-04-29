package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Scanner;

public class P53 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] goods = new int[n];
        int[] types = new int[n];
        for (int i = 0; i < n; i++) {
            goods[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            types[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        int minLimit = 0;
        int maxLimit = 0;
        for (int i = 0; i < n; i++) {
            minLimit = Math.max(minLimit, goods[i]);
            maxLimit += goods[i];
        }
        while (minLimit <= maxLimit) {
            int limit = (minLimit + maxLimit) / 2;
            if (canLoad(limit, n, goods, types, k)) {
                maxLimit = limit - 1;
            } else {
                minLimit = limit + 1;
            }
        }
        System.out.println(minLimit);
    }

    // 判断是否可以按照限制载货量将货物装载到中转车上
    public static boolean canLoad(int limit, int n, int[] goods, int[] types, int k) {
        int dryCarCount = 0; // 干货中转车数量
        int wetCarCount = 0; // 湿货中转车数量
        int dryCarSum = 0; // 当前干货中转车上已装载的货物总量
        int wetCarSum = 0; // 当前湿货中转车上已装载的货物总量
        for (int i = 0; i < n; i++) {
            if (types[i] == 0) { // 干货
                if (dryCarSum + goods[i] <= limit) { // 当前干货中转车还可以装载该供货商的货物
                    dryCarSum += goods[i];
                } else { // 当前干货中转车已经装载到限制量，需要使用下一辆干货中转车
                    if (dryCarCount + 1 == k) { // 已经达到最大干货中转车数量，无法继续装载
                        return false;
                    } else { // 使用下一辆干货中转车装载该供货商的货物
                        dryCarCount += 1;
                        dryCarSum = goods[i];
                    }
                }
            } else { // 湿货
                if (wetCarSum + goods[i] <= limit) { // 当前湿货中转车还可以装载该供货商的货物
                    wetCarSum += goods[i];
                } else { // 当前湿货中转车已经装载到限制量，需要使用下一辆湿货中转车
                    if (wetCarCount + 1 == k) { // 已经达到最大湿货中转车数量，无法继续装载
                        return false;
                    } else { // 使用下一辆湿货中转车装载该供货商的货物
                        wetCarCount += 1;
                        wetCarSum = goods[i];
                    }
                }
            }
        }
        return true;
    }
}

