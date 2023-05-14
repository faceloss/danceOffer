package codeforce.advance;

import java.util.*;

public class P01 {
    //将输入字符串按空格分割成整数数组
    public static List<Integer> split(String input_str) {
        List<Integer> nums = new ArrayList<>();
        while (input_str.indexOf(" ") != -1) {
            int found = input_str.indexOf(" "); //找到空格的位置
            nums.add(Integer.parseInt(input_str.substring(0, found))); //将空格前的字符串转换成整数并加入数组
            input_str = input_str.substring(found + 1); //将字符串截断，去掉已经处理过的部分
        }
        nums.add(Integer.parseInt(input_str)); //处理最后一个数字
        return nums;
    }

    //计算两个数中较短的时间
    public static int get_shorter_time(int a, int b) {
        if (a * 10 < b) { //如果a的十倍小于b，说明a可以在b之前完成
            return a * 10;
        }
        return b; //否则选择b
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入处理
        int N = Integer.parseInt(sc.nextLine().trim()); //读入一行字符串并转换成整数

        int T = Integer.parseInt(sc.nextLine().trim());

        String input_str = sc.nextLine().trim(); //读入一行字符串
        List<Integer> nums = split(input_str); //按空格分割字符串并转换成整数数组
        Collections.sort(nums); //将数组排序

        int[] dp = new int[N]; //定义dp数组，长度为N，初始值为0

        //初始状态 0 和 1
        dp[0] = nums.get(0); //dp[0]表示只选第一个数时的最短时间，即第一个数本身的值
        if (dp[0] > T) { //如果第一个数的值就已经超过了限制时间T，直接输出0 0
            System.out.println("0 0");
            return;
        }
        dp[1] = get_shorter_time(nums.get(0), nums.get(1)); //dp[1]表示只选前两个数时的最短时间，即第一个数和第二个数的较短时间
        if (dp[1] > T) { //如果只选前两个数的时间就已经超过了限制时间T，直接输出1 和 dp[0]
            System.out.println("1 " + dp[0]);
            return;
        }

        for (int i = 2; i < N; i++) { //从第三个数开始循环
            dp[i] = Math.min(dp[i - 1] + nums.get(0) + get_shorter_time(nums.get(0), nums.get(i)), //情况1：选择第i个数，需要加上dp[i-1]和前两个数的时间
                    dp[i - 2] + nums.get(0) + get_shorter_time(nums.get(i - 1), nums.get(i)) + nums.get(1) + get_shorter_time(nums.get(0), nums.get(1))); //情况2：不选第i个数，需要加上dp[i-2]和前两个数的时间
            if (dp[i] > T) { //如果当前时间已经超过了限制时间T，输出i和dp[i-1]
                System.out.println(i + " " + dp[i - 1]);
                return;
            }
        }

        System.out.println(N + " " + dp[N - 1]); //如果循环结束了还没有超过限制时间T，输出N和dp[N-1]
    }
}
