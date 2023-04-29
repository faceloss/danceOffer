package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:24
 **/

import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

class P70 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> nums =Arrays.stream(in.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        double min_diff = 256;
        Integer result = null;

        for (int i = -127; i <= 128; i++) {
            double sum = 0;
            for (int j=0;j<nums.size();j++) {
                sum += Math.max(0, Math.min(nums.get(j) + i, 255));
            }

            double diff = Math.abs(sum / nums.size() - 128);

            if (diff < min_diff) {
                min_diff = diff;
                result = i;
            } else if (diff == min_diff && result != null) {
                result = Math.min(result, i);
            }
        }

        System.out.println(result);
    }

}
