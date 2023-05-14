package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:01
 **/


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P27 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入
        List<Integer> params = Arrays.stream(scanner.nextLine().split(" ")) // 将输入的字符串按照空格分割成字符串数组
                .map(Integer::parseInt) // 将字符串数组中的每个元素转换为整数
                .collect(Collectors.toList()); // 将整数放入列表中
        int m = params.get(0); // 获取列表中第一个元素
        int n = params.get(1); // 获取列表中第二个元素
        int k = params.get(2); // 获取列表中第三个元素

        int x = Integer.parseInt(scanner.nextLine()); // 将下一行输入的字符串转换为整数

        while (scanner.hasNext()) { // 循环读取输入
            int price = Integer.parseInt(scanner.nextLine()); // 将下一行输入的字符串转换为整数

            // 计算四种优惠方式的结果
            int[][] result = new int[4][2]; // 创建一个二维数组
            result[0] = calculateModeA(price, m, n); // 计算第一种优惠方式的结果
            result[1] = calculateModeB(price, m, n); // 计算第二种优惠方式的结果
            result[2] = calculateModeC(price, m, k); // 计算第三种优惠方式的结果
            result[3] = calculateModeD(price, n, k); // 计算第四种优惠方式的结果

            // 按照价格降序，用券数降序排序
            Arrays.sort(result, (int[] a, int[] b) -> { // 使用 Lambda 表达式实现比较器
                if (a[0] != b[0]) { // 如果第一个数不相等
                    return a[0] - b[0]; // 按照第一个数降序
                } else { // 如果第一个数相等
                    return a[1] - b[1]; // 按照第二个数降序
                }
            });

            // 输出结果
            System.out.print(result[0][0]); // 输出第一个元素的第一个数
            System.out.print(" "); // 输出一个空格
            System.out.println(result[0][1]); // 输出第一个元素的第二个数
        }
    }

    // 先满减后打折
    public static int[] calculateModeA(int price, int m, int n) {
        int count = 0; // 用券数
        while (m > 0) { // 如果还可以使用满减券
            if (price < 100) { // 如果价格不足 100 元
                break; // 跳出循环
            }
            price -= (price / 100 * 10); // 满减
            count += 1; // 使用一张满减券
            m--; // 剩余满减券数减一
        }
        price *= 0.92; // 打折
        count += 1; // 使用一张打折券
        return new int[]{price, count}; // 返回价格和用券数
    }

    // 先打折后满减
    public static int[] calculateModeB(int price, int m, int n) {
        int count = 0; // 用券数
        price *= 0.92; // 打折
        count += 1; // 使用一张打折券
        while (m > 0) { // 如果还可以使用满减券
            if (price < 100) { // 如果价格不足 100 元
                break; // 跳出循环
            }
            price -= (price / 100 * 10); // 满减
            count += 1; // 使用一张满减券
            m--; // 剩余满减券数减一
        }
        return new int[]{price, count}; // 返回价格和用券数
    }

    // 先满减后无门槛
    public static int[] calculateModeC(int price, int m, int k) {
        int count = 0; // 用券数
        while (m > 0) { // 如果还可以使用满减券
            if (price < 100) { // 如果价格不足 100 元
                break; // 跳出循环
            }
            price -= (price / 100 * 10); // 满减
            count += 1; // 使用一张满减券
            m--; // 剩余满减券数减一
        }
        for (int i = 0; i < k; i++) { // 使用无门槛券
            price -= 5; // 优惠 5 元
            count += 1; // 使用一张无门槛券
            if (price < 0) { // 如果价格小于 0
                break; // 跳出循环
            }
        }
        return new int[]{price, count}; // 返回价格和用券数
    }

    // 先打折后无门槛
    public static int[] calculateModeD(int price, int n, int k) {
        int count = 0; // 用券数
        price *= 0.92; // 打折
        count += 1; // 使用一张打折券
        for (int i = 0; i < k; i++) { // 使用无门槛券
            price -= 5; // 优惠 5 元
            count += 1; // 使用一张无门槛券
            if (price < 0) { // 如果价格小于 0
                break; // 跳出循环
            }
        }
        return new int[]{price, count}; // 返回价格和用券数
    }

}

