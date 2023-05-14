package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:20
 **/

import java.util.*;
import java.lang.*;

class P64
{
    // 将字符串 params_str 以 op 分隔成字符串数组返回
    public static List<String> splitStringToStringArray(String params_str, String op) {
        List<String> str_array = new ArrayList<String>();
        while (params_str.indexOf(op) != -1) {
            int found = params_str.indexOf(op);
            str_array.add(params_str.substring(0, found));
            params_str = params_str.substring(found + 1);
        }

        str_array.add(params_str);
        return str_array;
    }

    // 比较两个字符串拼接起来的大小
    public static boolean compareStrings(String str1, String str2) {
        return Integer.parseInt(str1+str2) < Integer.parseInt(str2+str1);
    }

    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // 输入一行待排序的数字字符串，例如 "4 21 2"
        String input_str = sc.nextLine();

        // 将输入字符串以空格分隔成数字数组
        List<String> numbers = splitStringToStringArray(input_str, " ");

        // 使用自定义比较函数对字符串数组进行排序
        Collections.sort(numbers, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (compareStrings(str1, str2)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        // 如果最小的字符串以 '0' 开头，则从剩余的字符串中找一个不以 '0' 开头的字符串将其拼接到最小的字符串前面
        if (numbers.get(0).charAt(0) == '0') {
            for (int i = 1; i < numbers.size(); i++) {
                if (numbers.get(i).charAt(0) != '0') {
                    numbers.set(0, numbers.get(i) + numbers.get(0));
                    numbers.set(i, "");
                    break;
                }
            }
        }

        // 将排序后的字符串数组拼接成一个字符串
        String result = "";
        for (String str : numbers) {
            result += str;
        }

        // 去掉结果字符串开头多余的 '0' 并输出
        System.out.println(result.replaceFirst("^0+(?!$)", ""));
    }
}

