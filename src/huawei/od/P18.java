package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:00
 **/


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class P18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();

        // 用map记录B中每个字符所在的位置
        Map<Character, Integer> positions = new HashMap<Character, Integer>();
        for (int i = 0; i < B.length(); i++) {
            positions.put(B.charAt(i), i);
        }

        Vector<Integer> result = new Vector<Integer>(B.length());
        for (int i = 0; i < B.length(); i++) {
            result.add(0);
        }

        for (int i = 0; i < A.length(); i++) {
            if (positions.containsKey(A.charAt(i))) { // 如果A中存在B中的字符
                int idx = positions.get(A.charAt(i)); // 记录该字符在B中的位置
                if (idx == 0 || result.get(idx) < result.get(idx - 1)) { // 如果是B中的第一个字符或者该字符在B中的前一个字符已经被匹配过了
                    result.set(idx, result.get(idx) + 1); // 更新result数组
                }
            }
        }

        System.out.println(result.get(result.size() - 1)); // 输出result数组中最后一个元素
    }
}

