package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.*;

public class P48 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读入谜面单词列表和谜底库单词列表
        String[] puzzles = scanner.nextLine().split(",");
        String[] answers = scanner.nextLine().split(",");

        // 存放每个谜面对应的谜底，如果找不到则为 "not found"
        List<String> results = new ArrayList<>();

        for (String puzzle : puzzles) {
            // 获取谜面经过去重和排序后的字符串
            String sortedPuzzle = getSortedAndDistinctStr(puzzle);

            // 遍历谜底库中的每个单词
            boolean found = false;
            for (String answer : answers) {
                // 获取谜底经过去重和排序后的字符串
                String sortedAnswer = getSortedAndDistinctStr(answer);

                // 如果谜面和谜底经过去重和排序后的字符串相等，则找到了对应的谜底
                if (sortedPuzzle.equals(sortedAnswer)) {
                    results.add(answer);
                    found = true;
                }
            }

            // 如果没有找到对应的谜底，则将结果设为 "not found"
            if (!found) {
                results.add("not found");
            }
        }

        // 输出结果
        System.out.println(String.join(",", results));
    }

    // 获取字符串经过去重和排序后的结果
    public static String getSortedAndDistinctStr(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }

        List<Character> list = new ArrayList<>(set);
        Collections.sort(list);

        StringBuilder builder = new StringBuilder();
        for (char c : list) {
            builder.append(c);
        }

        return builder.toString();
    }
}

