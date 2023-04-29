package huawei.advance;

import java.util.*;

public class P34 {
    public static void main(String[] args) {
        int sub_arr_pos = 0; // 最大几何平均值子数组的起始位置
        int sub_arr_size = 0; // 最大几何平均值子数组的长度
        int N, L;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt(); // 输入数组大小和子数组最小长度

        double min_num = Double.MAX_VALUE; // 数组中的最小值
        double max_num = Double.MIN_VALUE; // 数组中的最大值
        double result = 1; // 数组前L个数的乘积

        List<Double> nums = new ArrayList<>(); // 数组
        for (int i = 0; i < N; i++) {
            nums.add(sc.nextDouble());
            min_num = Math.min(min_num, nums.get(i)); // 更新最小值
            max_num = Math.max(max_num, nums.get(i)); // 更新最大值
            if (i < L) {
                result *= nums.get(i); // 计算前L个数的乘积
            }
        }

        // 二分法查找最大几何平均值
        while (max_num - min_num >= Math.pow(10, -10)) {
            double mid_num = (min_num + max_num) / 2; // 计算中间值

            double temp_result = result; // 临时乘积
            boolean flag = false; // 是否找到最大几何平均值子数组的标志位

            // 从前往后遍历数组，计算每个子数组的几何平均值
            for (int i = L; i <= N; i++) {
                if (temp_result >= Math.pow(mid_num, L)) { // 如果临时乘积大于等于当前中间值的L次方，说明当前子数组的几何平均值大于等于中间值
                    sub_arr_pos = i - L; // 更新最大几何平均值子数组的起始位置
                    sub_arr_size = L; // 更新最大几何平均值子数组的长度
                    flag = true; // 找到最大几何平均值子数组
                    break;
                }
                if (i == N) { // 如果遍历到数组末尾，结束循环
                    break;
                }
                temp_result /= nums.get(i - L); // 除去子数组的第一个数
                temp_result *= nums.get(i); // 加上子数组的最后一个数
            }

            if (flag) { // 如果找到最大几何平均值子数组，更新最小值为中间值
                min_num = mid_num;
            } else { // 如果没找到，更新最大值为中间值
                max_num = mid_num;
            }
        }

        System.out.println(sub_arr_pos + " " + sub_arr_size); // 输出最大几何平均值子数组的起始位置和长度
    }
}
