package huawei.advance;

import java.util.ArrayList; // 导入 ArrayList 类
import java.util.List; // 导入 List 类
import java.util.Scanner; // 导入 Scanner 类

public class P27 {
    private static int[] prices; // 物品价格数组
    private static int budget; // 预算
    private static List<List<Integer>> resultList = new ArrayList<>(); // 符合预算的购买情况列表

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象
        budget = scanner.nextInt(); // 读取预算
        scanner.nextLine(); // 读取换行符
        String[] priceStrings = scanner.nextLine().replace("[", "").replace("]", "").split(","); // 读取物品价格字符串并处理成数组
        prices = new int[priceStrings.length]; // 创建物品价格数组
        for (int i = 0; i < priceStrings.length; i++) { // 遍历物品价格字符串数组
            prices[i] = Integer.valueOf(priceStrings[i].trim()); // 将字符串转换为整数，并存入物品价格数组
        }
        handle(0, 0, new ArrayList<>()); // 调用 handle 方法，开始递归
        System.out.println(resultList); // 输出符合预算的购买情况列表
    }

    /**
     * 通过递归求出所有的购买情况
     *
     * @param index 物品价格索引
     * @param count 购买物品总价格
     * @param list  购买物品集合
     */
    private static void handle(int index, int count, List<Integer> list) {
        if (budget <= count) { // 如果物品总价格大于等于预算总价格，则退出递归
            List<Integer> tempList = new ArrayList<>(list); // 创建临时集合，存储当前购买物品集合的副本
            if (budget == count) { // 如果物品总价格等于预算总价格，则将当前购买物品集合添加到符合预算的购买情况列表中
                resultList.add(tempList);
            }
        } else { // 如果物品总价格小于预算总价格，则继续递归
            for (int i = index; i < prices.length; i++) { // 从当前物品价格索引开始遍历物品价格数组
                list.add(prices[i]); // 将当前物品价格添加到购买物品集合中
                handle(i, count + prices[i], list); // 递归调用 handle 方法，更新物品价格索引和购买物品总价格，并将购买物品集合作为参数传入
                list.remove(list.size() - 1); // 递归返回后，将购买物品集合中的最后一个元素移除，以便下一次遍历
            }
        }
    }
}

