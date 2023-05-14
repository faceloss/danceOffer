package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:20
 **/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P59_CouponCalculation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //获取输入参数
        List<Integer> params = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int m = params.get(0);
        int n = params.get(1);
        int k = params.get(2);

        int x = Integer.parseInt(scanner.nextLine());

        while (scanner.hasNext()) {
            int price = Integer.parseInt(scanner.nextLine());

            int[][] result = new int[4][2];

            //分别调用4种计算方式，得到方案及其需要的优惠券数量
            result[0] = getModeA(price, m, n);
            result[1] = getModeB(price, m, n);
            result[2] = getModeC(price, m, k);
            result[3] = getModeD(price, n, k);

            //按照价格降序排序，如果价格相同，则按照优惠券数量降序排序
            Arrays.sort(result, (int[] a, int[] b) -> {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            });

            //输出最佳方案所需的优惠券数量
            System.out.print(result[0][0]);
            System.out.print(" ");
            System.out.println(result[0][1]);
        }

        scanner.close();
    }

    /**
     * 先满减后打折的计算方式
     */
    public static int[] getModeA(int price, int m, int n) {
        int count = 0;
        count += Math.min(m, price / 100);
        price -= count * 10;
        price *= 0.92;
        count += 1;
        return new int[]{price, count};
    }

    /**
     * 先打折后满减的计算方式
     */
    public static int[] getModeB(int price, int m, int n) {
        int count = 0;
        price *= 0.92;
        count += 1;
        count += Math.min(m, price / 100);
        price -= (count-1) * 10;
        return new int[]{price, count};
    }

    /**
     * 先满减后无门槛的计算方式
     */
    public static int[] getModeC(int price, int m, int k) {
        int count = 0;
        count += Math.min(m, price / 100);
        price -= count * 10;
        for (int i=0; i<k; i++) {
            price -= 5;
            count += 1;
            if (price < 0) {
                break;
            }
        }
        return new int[]{price, count};
    }

    /**
     * 先打折后无门槛的计算方式
     */
    public static int[] getModeD(int price, int n, int k) {
        int count = 0;
        price *= 0.92;
        count += 1;
        for (int i=0; i<k; i++) {
            price -= 5;
            count += 1;
            if (price < 0) {
                break;
            }
        }
        return new int[]{price, count};
    }
}
