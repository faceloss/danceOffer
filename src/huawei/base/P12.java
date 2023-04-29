package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 21:59
 **/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
            } else {
                count.put(nums[i], 1);
            }
        }
        int maxCount = 0;
        for (int key : count.keySet()) {
            maxCount = Math.max(maxCount, count.get(key));
        }
        int result = n;
        for (int key : count.keySet()) {
            if (count.get(key) == maxCount) {
                int first = -1;
                int last = -1;
                for (int i = 0; i < n; i++) {
                    if (nums[i] == key) {
                        if (first == -1) {
                            first = i;
                        }
                        last = i;
                    }
                }
                result = Math.min(result, last - first + 1);
            }
        }
        System.out.println(result);
    }
}

