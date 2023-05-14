package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 21:58
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class P11 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int itemNumber = in.nextInt(); // 商品数量
        int days = in.nextInt(); // 售货天数
        List<Integer> maxItems = new ArrayList<>(); // 每件商品最大持有数量
        for (int i = 0; i < itemNumber; i++) {
            maxItems.add(in.nextInt());
        }
        List<List<Integer>> prices = new ArrayList<>(); // 商品价格列表
        for (int i = 0; i < itemNumber; i++) {
            List<Integer> itemPrice = new ArrayList<>();
            for (int j = 0; j < days; j++) {
                itemPrice.add(in.nextInt());
            }
            prices.add(itemPrice);
        }

        // 贪心算法
        int maxProfit = 0;
        for (int i = 0; i < prices.size(); i++) { // 遍历每件商品
            int ans = 0;
            for (int j = 1; j < prices.get(i).size(); j++) { // 遍历商品价格列表，求出每天的利润
                ans += Math.max(0, prices.get(i).get(j) - prices.get(i).get(j - 1));
                // 当前价格减去前一天价格，如果为负数则代表亏本，不计入利润
            }
            maxProfit += ans * maxItems.get(i); // 求出当前商品能够获取的最大利润
        }

        System.out.println(maxProfit); // 输出最大利润
    }
}

