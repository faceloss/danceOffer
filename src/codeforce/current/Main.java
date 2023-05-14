package codeforce.current;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextDouble()) { // 注意 while 处理多个 case
            double val = in.nextDouble();
            System.out.println(getRes(val));
        }
    }
    public static double getRes(double val){
        boolean flag = val >= 0;
        val = Math.abs(val);
        val = val * 1000;
        double res = -1;
        double base = Integer.MAX_VALUE;
        for(double i= val; i >= 1; i++){
            double temp = i * i * i;
            if(val - temp < base){
                base = Math.abs(val - temp);
                res = i;
            }
        }
        return flag ? res : -res;
    }
}