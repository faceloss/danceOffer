package huawei.current;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @program: danceOffer
 * @description: 核酸检测人员安排
 * 由于外界变化，采样员的效率会以M人/小时为粒度发生变化，M为采样效率浮动粒度，M=N10%，输入保证N10%的结果为整数。
 * 采样员效率浮动规则：采样员需要一名志愿者协助组织才能发挥正常效率，在此基础上，每增加一名志愿者，效率提升1M，最多提升3M；
 * 如果没有志愿者协助组织，效率下降2M。
 * 第一行一个值，采样员人数，取值范围[1,100];
 * 第二个值，志愿者人数，取值范围[1,500];
 * 第二行：各采样员基准效率值（单位人/小时），取值范围[60,600],保证序列中每项值计算10%为整数。
 * 输出描述
 * 第一行：总最快检测效率（单位人/小时）
 * @author: mobing_yin
 * @create: 2023-04-29 21:24
 **/

public class Solution_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int[] arr = new int[num1];
        for (int i = 0; i < num1 ; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(finBast(num1, num2, arr));

    }

    public static double finBast(int m, int n, int[] arr){
        Queue<Double> stack = new PriorityQueue<Double>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        double res = 0;
        for(double num : arr){
            res += 0.8 * num;
            stack.add(0.2 * num);
            stack.add(0.1 * num);
            stack.add(0.1 * num);
            stack.add(0.1 * num);
        }
        //  3 ,3 ,100 200 300==>60 30 30 30 40 20 20   130 + 600 * 8 480 = 610
        // -2M 0 M 2M 3M,0 1 2 3 4 志愿者
        //每个人默认都是没有志愿者，-2M
        while(n > 0 || stack.isEmpty()){
            //单位志愿者可以获得最大效率,从0到1效率最大
            double curNum = stack.poll();
            res += curNum;
            n--;
        }
        return res;
    }
}
