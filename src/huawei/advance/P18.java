package huawei.advance;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
 
public class P18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine()); // 读入n
 
        int[][] ranges = new int[n][2]; // 定义一个n行2列的二维数组ranges
        for (int i = 0; i < n; i++) { // 循环读入n个区间
            String[] str = sc.nextLine().split(","); // 读入字符串并用逗号分隔
            ranges[i][0] = Integer.parseInt(str[0]); // 第一个数字作为区间起点
            ranges[i][1] = Integer.parseInt(str[1]); // 第二个数字作为区间终点
        }
 
        System.out.println(getResult(ranges)); // 输出结果
    }
 
    public static int getResult(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]); // 按照区间起点从小到大排序
 
        LinkedList<int[]> stack = new LinkedList<>(); // 定义一个栈
        stack.add(ranges[0]); // 将第一个区间加入栈中
 
        for (int i = 1; i < ranges.length; i++) { // 循环处理剩余的区间
            int[] range = ranges[i]; // 取出当前区间
            while (true) { // 不断进行以下判断，直到当前区间被处理完毕
                if (stack.isEmpty()) { // 如果栈为空，将当前区间加入栈中
                    stack.add(range);
                    break;
                }
 
                int[] top = stack.getLast(); // 取出栈顶区间
                int s0 = top[0]; // 栈顶区间的起点
                int e0 = top[1]; // 栈顶区间的终点
                int s1 = range[0]; // 当前区间的起点
                int e1 = range[1]; // 当前区间的终点
 
                if (s1 <= s0) { // 如果当前区间的起点在栈顶区间内或者相等
                    if (e1 <= s0) { // 如果当前区间被栈顶区间包含
                        break; // 直接退出循环
                    } else if (e1 < e0) { // 如果当前区间的终点在栈顶区间内
                        break; // 直接退出循环
                    } else { // 如果当前区间的终点在栈顶区间的右边
                        stack.removeLast(); // 将栈顶区间弹出
                    }
                } else if (s1 < e0) { // 如果当前区间的起点在栈顶区间的右边但是和栈顶区间相交
                    if (e1 <= e0) { // 如果当前区间被栈顶区间包含
                        break; // 直接退出循环
                    } else { // 如果当前区间的终点在栈顶区间的右边
                        stack.add(new int[] {e0, e1}); // 将栈顶区间的终点和当前区间的终点作为新的区间加入栈中
                        break; // 直接退出循环
                    }
                } else { // 如果当前区间的起点在栈顶区间的右边且不相交
                    stack.add(range); // 将当前区间加入栈中
                    break; // 直接退出循环
                }
            }
        }
 
        return stack.size(); // 返回栈的大小
    }
}
