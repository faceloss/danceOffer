package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:17
 **/

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

class P35 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); // 读取节点个数
        List<Integer> nodes = Arrays.stream(scanner.nextLine().split(" ")) // 读取节点编号
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.sort(nodes); // 排序
        int median = 0;
        if (n % 2 == 0) {
            median = nodes.get((n - 1) / 2); // 取中间两个数的平均值
        } else {
            median = nodes.get(n / 2); // 取中间数
        }
        System.out.println(median); // 输出中位数
    }

}

