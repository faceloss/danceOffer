package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:01
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入的可用处理器信息
        List<Integer> availableProcessors = Arrays.stream(scanner.nextLine().split("[\\[\\]\\,\\s]"))
                .filter(str -> !"".equals(str))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // 读取输入的 num 值
        int num = scanner.nextInt();

        // 调用 getResult 方法并输出结果
        System.out.println(getResult(availableProcessors, num));
    }

    // 定义 getResult 方法，用于计算可用处理器组合情况
    public static String getResult(List<Integer> availableProcessors, int num) {
        // 定义两个列表，用于存放处理器数量小于 4 和大于等于 4 的处理器
        List<Integer> link1 = new ArrayList<>();
        List<Integer> link2 = new ArrayList<>();

        // 对可用处理器列表进行排序，并将处理器数量小于 4 的处理器加入 link1 列表，大于等于 4 的处理器加入 link2 列表
        availableProcessors.sort(Integer::compareTo);
        for (Integer processor : availableProcessors) {
            if (processor < 4) {
                link1.add(processor);
            } else {
                link2.add(processor);
            }
        }

        // 定义一个列表，用于存放处理器组合情况
        List<List<Integer>> combinations = new ArrayList<>();

        // 根据 num 值的不同，计算不同的处理器组合情况
        switch (num) {
            case 1:
                if (link1.size() == 1 || link2.size() == 1) {
                    if (link1.size() == 1) {
                        dfs(link1, 0, 1, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 1) {
                        dfs(link2, 0, 1, new ArrayList<>(), combinations);
                    }
                } else if (link1.size() == 3 || link2.size() == 3) {
                    if (link1.size() == 3) {
                        dfs(link1, 0, 1, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 3) {
                        dfs(link2, 0, 1, new ArrayList<>(), combinations);
                    }
                } else if (link1.size() == 2 || link2.size() == 2) {
                    if (link1.size() == 2) {
                        dfs(link1, 0, 1, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 2) {
                        dfs(link2, 0, 1, new ArrayList<>(), combinations);
                    }
                } else if (link1.size() == 4 || link2.size() == 4) {
                    if (link1.size() == 4) {
                        dfs(link1, 0, 1, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 4) {
                        dfs(link2, 0, 1, new ArrayList<>(), combinations);
                    }
                }
                break;
            case 2:
                if (link1.size() == 2 || link2.size() == 2) {
                    if (link1.size() == 2) {
                        dfs(link1, 0, 2, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 2) {
                        dfs(link2, 0, 2, new ArrayList<>(), combinations);
                    }
                } else if (link1.size() == 4 || link2.size() == 4) {
                    if (link1.size() == 4) {
                        dfs(link1, 0, 2, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 4) {
                        dfs(link2, 0, 2, new ArrayList<>(), combinations);
                    }
                } else if (link1.size() == 3 || link2.size() == 3) {
                    if (link1.size() == 3) {
                        dfs(link1, 0, 2, new ArrayList<>(), combinations);
                    }
                    if (link2.size() == 3) {
                        dfs(link2, 0, 2, new ArrayList<>(), combinations);
                    }
                }
                break;
            case 4:
                if (link1.size() == 4 || link2.size() == 4) {
                    if (link1.size() == 4) {
                        combinations.add(link1);
                    }
                    if (link2.size() == 4) {
                        combinations.add(link2);
                    }
                }
                break;
            case 8:
                if (link1.size() == 4 && link2.size() == 4) {
                    combinations.add(Stream.concat(link1.stream(), link2.stream())
                            .collect(Collectors.toList()));
                }
                break;
        }

        // 将处理器组合情况转换为字符串并返回
        return combinations.toString();
    }

    // 定义 dfs 方法，用于计算处理器组合情况
    public static void dfs(List<Integer> processors, int index, int level, List<Integer> path,
                           List<List<Integer>> combinations) {
        // 如果 path 列表中的元素数量等于 level，表示已经找到了一种处理器组合情况，将其加入 combinations 列表中
        if (path.size() == level) {
            combinations.add(new ArrayList<>(path));
        }

        // 遍历 processors 列表中 index 位置之后的元素，并将其加入 path 列表中，然后递归调用 dfs 方法
        for (int i = index; i < processors.size(); i++) {
            path.add(processors.get(i));
            dfs(processors, i + 1, level, path, combinations);
            path.remove(path.size() - 1);
        }
    }
}

