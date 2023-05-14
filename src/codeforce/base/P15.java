package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:00
 **/
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class P15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] newspaper = scanner.nextLine().split(" "); // 输入报纸内容
        String[] anonymousLetter = scanner.nextLine().split(" "); // 输入匿名信内容

        boolean canSplice = canSplice(newspaper, anonymousLetter); // 判断报纸内容是否可以拼成匿名信

        System.out.println(canSplice); // 输出结果
    }

    /**
     * 判断报纸内容是否可以拼成匿名信
     *
     * @param newspaper       报纸内容
     * @param anonymousLetter 匿名信内容
     * @return true：可以拼成，false：不能拼成
     */
    public static boolean canSplice(String[] newspaper, String[] anonymousLetter) {
        HashMap<String, Integer> count = new HashMap<>(); // 用于存储报纸中每个单词的字母数量
        for (String str : newspaper) {
            String sortedStr = sortString(str); // 对单词中的字母进行排序
            count.put(sortedStr, count.getOrDefault(sortedStr, 0) + 1); // 统计单词的字母数量
        }

        boolean canSplice = true; // 是否可以拼成匿名信
        for (String str : anonymousLetter) {
            String sortedStr = sortString(str); // 对单词中的字母进行排序

            if (count.containsKey(sortedStr) && count.get(sortedStr) > 0) { // 如果报纸中包含该单词
                count.put(sortedStr, count.get(sortedStr) - 1); // 将该单词的数量减1
            } else {
                canSplice = false; // 如果报纸中不包含该单词，则不能拼成匿名信
                break;
            }
        }

        return canSplice; // 返回是否可以拼成匿名信
    }

    /**
     * 对字符串中的字母进行排序
     *
     * @param str 字符串
     * @return 排序后的字符串
     */
    public static String sortString(String str) {
        char[] charArray = str.toCharArray(); // 将字符串转换为字符数组
        Arrays.sort(charArray); // 对字符数组进行排序
        return new String(charArray); // 将排序后的字符数组转换为字符串并返回
    }
}




