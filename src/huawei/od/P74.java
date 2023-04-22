package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:17
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P74 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // 客户列表，按照优先级从高到低分别为a[1]~a[5]
        List<List<Integer>> customers = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            customers.add(new ArrayList<>());
        }

        // 处理每个操作
        for (int i = 0; i < n; i++) {
            String op = in.next();
            if (op.equals("a")) { // 添加客户
                int id = in.nextInt();
                int priority = in.nextInt();
                customers.get(priority).add(id); // 将客户添加到对应优先级的列表中
            } else { // 处理下一个客户
                for (int j = 1; j <= 5; j++) { // 从高到低依次遍历客户列表
                    if (!customers.get(j).isEmpty()) { // 如果该优先级的客户列表不为空
                        System.out.println(customers.get(j).remove(0)); // 输出该客户的编号，并从列表中删除
                        break; // 处理完一个客户后结束循环
                    }
                }
            }
        }
    }
}
