package huawei.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 最小调整序列次数：给一个数据结构可以队头队尾添加，删除只能从队头。
 * 先依次添加1～n,再移出n次保证按1～n移出,统计删除队头时候调整次数（调整就是排序从队头删除顺序数）
 * */
public class P02_MinNum_Adjust_Sequence {
    public static List<String> split(String input_str) {
        //空格分割
        List<String> v = new ArrayList<>();
        while (input_str.indexOf(" ") != -1) {
            int found = input_str.indexOf(" ");
            v.add(input_str.substring(0, found));
            input_str = input_str.substring(found + 1);
        }
        v.add(input_str);
        return v;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_number_str = scanner.nextLine();
        int number = Integer.parseInt(input_number_str);//数据的范围

        List<List<String>> operations = new ArrayList<>();//添加和删除语句
        for (int i = 0; i < 2 * number; i++) {
            String input_str = scanner.nextLine();
            operations.add(split(input_str));
        }

        int queue_size = 0;//队列长度
        boolean in_order = true;//是否按顺序删除
        int result = 0;//最小的调整顺序次数

        for (int i = 0; i < operations.size(); i++) {
            List<String> operation = operations.get(i);
            if (operation.get(0).equals("head")) {//从头部添加元素
                if (queue_size > 0 && in_order) {//不按顺序删除
                    in_order = false;
                }
                queue_size++;
            } else if (operation.get(0).equals("tail")) {//从尾部添加元素
                queue_size++;
            } else {//删除元素
                if (queue_size == 0) {
                    continue;
                }
                if (!in_order) {//不按顺序删除
                    result++;
                    in_order = true;
                }
                queue_size--;
            }
        }

        System.out.println(result);//输出最小的调整顺序次数
    }
}
