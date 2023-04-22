package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * 题目描述
 *
 * 记账本上记录了若干条多国货币金额，需要转换成人民币分（fen），汇总后输出。
 * 每行记录一条金额，金额带有货币单位，格式为数字+单位，可能是单独元，或者单独分，或者元与分的组合。
 * 要求将这些货币全部换算成人民币分（fen）后进行汇总，汇总结果仅保留整数，小数部分舍弃。
 * 元和分的换算关系都是1:100，如下：
 *
 * 1CNY=100fen（1元=100分）
 * 1HKD=100cents（1港元=100港分）
 * 1JPY=100sen（1日元=100仙）
 * 1EUR=100eurocents（1欧元=100欧分）
 * 1GBP=100pence（1英镑=100便士）
 *
 * 汇率表如下：
 *
 * CNY	JPY	HKD	EUR	GBP
 * 100	1825	123	14	12
 *
 * 即：100CNY = 1825JPY = 123HKD = 14EUR = 12GBP
 *
 * 输入描述
 *
 * 第一行输入为N，N表示记录数。0<N<100
 *
 * 之后N行，每行表示一条货币记录，且该行只会是一种货币。
 *
 * 输出描述
 *
 * 将每行货币转换成人民币分（fen）后汇总求和，只保留整数部分。
 * 输出格式只有整数数字，不带小数，不带单位。
 *
 * 用例
 * 输入	1
 * 100CNY
 * 输出	10000
 * 说明	100CNY转换后是10000fen，所以输出结果为10000
 * @author: mobing_yin
 * @create: 2023-04-22 21:55
 **/

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P09 {
    public static void main(String[] args) {
        // 输入货币数量
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // 创建字符串数组，存储输入的货币字符串
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.next();
        }

        // 正则表达式匹配货币金额
        String pattern_str = "(\\d+)((CNY)|(JPY)|(HKD)|(EUR)|(GBP)|(fen)|(cents)|(sen)|(eurocents)|(pence))";
        Pattern pattern = Pattern.compile(pattern_str);

        // 创建哈希表，存储货币单位对应的汇率
        HashMap<String, Double> exchange_rate = new HashMap<>();
        exchange_rate.put("CNY", 100.0);
        exchange_rate.put("JPY", 100.0 / 1825 * 100);
        exchange_rate.put("HKD", 100.0 / 123 * 100);
        exchange_rate.put("EUR", 100.0 / 14 * 100);
        exchange_rate.put("GBP", 100.0 / 12 * 100);
        exchange_rate.put("fen", 1.0);
        exchange_rate.put("cents", 100.0 / 123);
        exchange_rate.put("sen", 100.0 / 1825);
        exchange_rate.put("eurocents", 100.0 / 14);
        exchange_rate.put("pence", 100.0 / 12);

        // 计算总共的货币数量（以分为单位）
        double total_fen = 0.0;
        for (int i = 0; i < n; i++) {
            // 使用正则表达式匹配货币字符串中的金额和单位
            Matcher matcher = pattern.matcher(arr[i]);
            while (matcher.find()) {
                double amount = Double.parseDouble(matcher.group(1)); // 将金额字符串转换为 double 类型
                String unit = matcher.group(2);
                total_fen += amount * exchange_rate.get(unit); // 计算该货币的总共金额（以分为单位）
            }
        }

        // 输出总共的货币数量（以分为单位）
        System.out.println((int) total_fen);
    }
}
