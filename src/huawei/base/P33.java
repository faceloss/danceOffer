package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/

import java.util.*;
import java.util.stream.Collectors;

public class P33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读入需要处理的组数
        int groupNum = Integer.parseInt(sc.nextLine());

        // M,N不在限定范围内，统一输出一组空数组[[]]
        if (groupNum > 10 || groupNum < 1) {
            System.out.println("[[]]");
            return;
        }

        // 用来存储每个区间的 TreeSet
        ArrayList<TreeSet<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            // 读入每个区间的端口号，并将其转换为 TreeSet 存储
            List<Integer> tmp = Arrays.stream(sc.nextLine().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            // M,N不在限定范围内，统一输出一组空数组[[]]
            int size = tmp.size();
            if (size < 1 || size > 100) {
                System.out.println("[[]]");
                return;
            }

            groups.add(new TreeSet<>(tmp));
        }

        // 调用 getResult 方法进行区间合并，并输出结果
        System.out.println(getResult(groups, groupNum));
    }

    // 对所有区间进行合并，并返回结果
    public static String getResult(ArrayList<TreeSet<Integer>> groups, int groupNum) {
        // 外层循环，用来判断是否还有区间可以合并
        outer:
        while (true) {
            // 从后往前遍历每个区间
            for (int i = groupNum - 1; i >= 0; i--) {
                TreeSet<Integer> group1 = groups.get(i);
                // 如果当前区间已经为空，则跳过
                if (group1.size() == 0) continue;

                // 从当前区间的前一个区间开始往前遍历
                for (int j = i - 1; j >= 0; j--) {
                    TreeSet<Integer> group2 = groups.get(j);
                    // 如果当前区间已经为空，则跳过
                    if (group2.size() == 0) continue;

                    // 判断两个区间是否可以合并
                    if (hasTwoSamePort(group1, group2)) {
                        group2.addAll(group1);
                        group1.clear();
                        // 如果有区间合并成功，则重新开始外层循环
                        continue outer;
                    }
                }
            }
            // 如果所有区间都无法合并，则跳出循环
            break;
        }

        // 将所有非空的区间转换为 List，并输出
        return groups.stream().filter(group -> group.size() > 0).collect(Collectors.toList()).toString();
    }

    // 判断两个区间是否可以合并
    public static boolean hasTwoSamePort(TreeSet<Integer> group1, TreeSet<Integer> group2) {
        int count = 0;
        for (Integer val : group1) {
            if (group2.contains(val)) {
                if (++count >= 2) return true;
            }
        }

        return false;
    }
}

