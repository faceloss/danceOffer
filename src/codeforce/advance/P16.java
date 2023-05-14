package codeforce.advance;

import java.util.*;

public class P16 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String range_str = input.next(); // 输入区间字符串
        range_str = range_str.replaceAll("\\[|\\]", ""); // 去掉左右括号
        String[] temp_ranges = range_str.split(","); // 切分区间字符串
        List<int[]> ranges = new ArrayList<>(); // 定义列表ranges存储区间
        for (int i = 0; i < temp_ranges.length; i += 2) { // 遍历切分结果
            int[] single_range = new int[2]; // 定义数组single_range存储单个区间
            single_range[0] = Integer.parseInt(temp_ranges[i]); // 将左端点加入single_range
            single_range[1] = Integer.parseInt(temp_ranges[i+1]); // 将右端点加入single_range
            ranges.add(single_range); // 将single_range加入ranges
        }
        String connector_str = input.next(); // 输入区间连接器字符串
        connector_str = connector_str.replaceAll("\\[|\\]", ""); // 去掉左右括号
        String[] temp_connectors = connector_str.split(","); // 切分区间连接器字符串
        List<Integer> connectors = new ArrayList<>(); // 定义列表connectors存储区间连接器
        for (String temp_connector : temp_connectors) { // 遍历切分结果
            connectors.add(Integer.parseInt(temp_connector)); // 将连接器加入connectors
        }

        // 区间合并
        Collections.sort(ranges, new Comparator<int[]>() { // 对区间进行排序
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] > b[0]) { // 如果a区间的左端点大于b区间的左端点
                    return 1; // a排在b后面
                } else if (a[0] == b[0]) { // 如果a区间的左端点等于b区间的左端点
                    if (a[1] > b[1]) { // 如果a区间的右端点大于b区间的右端点
                        return 1; // a排在b后面
                    }
                }
                return -1; // a排在b前面
            }
        });
        List<int[]> merge_ranges = new ArrayList<>(); // 定义列表merge_ranges存储合并后的区间
        merge_ranges.add(ranges.get(0)); // 将第一个区间加入merge_ranges
        List<Integer> range_diffs = new ArrayList<>(); // 定义列表range_diffs存储相邻区间的距离
        for (int i = 1; i < ranges.size(); i++) { // 遍历区间
            int[] range1 = merge_ranges.get(merge_ranges.size() - 1); // 取出merge_ranges中最后一个区间
            int[] range2 = ranges.get(i); // 取出当前区间
            if (range2[0] <= range1[1]) { // 如果当前区间与最后一个区间有交集
                merge_ranges.remove(merge_ranges.size() - 1); // 弹出最后一个区间
                merge_ranges.add(new int[]{range1[0], Math.max(range1[1], range2[1])}); // 将合并后的区间加入merge_ranges
            } else {
                range_diffs.add(range2[0] - range1[1]); // 计算当前区间与最后一个区间的距离并加入range_diffs
                merge_ranges.add(range2); // 将当前区间加入merge_ranges
            }
        }

        // 区间连接器使用
        Collections.sort(range_diffs); // 对区间距离进行排序
        Collections.sort(connectors); // 对区间连接器进行排序
        int result = merge_ranges.size(); // 初始化结果为合并后的区间数
        int idx = 0; // 初始化区间距离的索引为0
        for (int i = 0; i < connectors.size() && idx < range_diffs.size(); i++) { // 遍历区间连接器
            if (connectors.get(i) >= range_diffs.get(idx)) { // 如果当前连接器可以连接相邻区间
                idx++; // 将区间距离索引加1
                result--; // 将结果减1
            }
        }

        // 输出结果
        System.out.println(result); // 输出最终结果
    }
}
