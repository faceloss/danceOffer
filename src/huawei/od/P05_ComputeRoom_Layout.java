package huawei.od;

import java.util.Scanner;
/**
 * @program: danceOffer
 * @description: 机房布局：每一个机柜旁边需要一个电箱，问至少需要多少个电箱MIIM,I是间隔 M是电箱
 * MIIM，需要在间隔上放两个电箱
 * @author: mobing_yin
 * @create: 2023-04-22 17:03
 **/
public class P05_ComputeRoom_Layout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next(); // 输入字符串
        int n = str.length(); // 字符串长度
        int cabinet = -1, box = -1; // 记录当前机柜和电箱的位置，初始化为 -1
        int ans = 0; // 记录答案，初始化为 0
        for (int i = 0; i < n; i++) { // 遍历字符串
            if (str.charAt(i) == 'M') { // 如果当前是 M
                if (cabinet != -1 && box != -1) { // 如果前面已经有一个 I 和 M
                    ans += Math.abs(cabinet - box) + 1; // 计算距离并累加到答案中
                    cabinet = box = -1; // 重置机柜和电箱的位置
                }
                if (cabinet == -1) cabinet = i; // 如果当前是第一个 M，记录机柜的位置
                else box = i; // 如果当前是第二个 M，记录电箱的位置
            }
            else if (str.charAt(i) == 'I') { // 如果当前是 I，与 M 的处理方式相同
                if (cabinet != -1 && box != -1) {
                    ans += Math.abs(cabinet - box) + 1;
                    cabinet = box = -1;
                }
                if (box == -1) box = i;
                else cabinet = i;
            }
            else { // 如果当前是其他字符，即出现了 MM 或 II，直接返回 -1
                ans = -1;
                break;
            }
        }
        if (ans == 0) ans = -1; // 如果没有任何电箱，返回 -1
        System.out.println(ans); // 输出答案
    }
}
