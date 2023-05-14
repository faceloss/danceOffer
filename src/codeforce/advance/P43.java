package codeforce.advance;

import java.util.*;

public class P43 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            nums.add(num);
        }
        Collections.sort(nums);

        List<Integer> pairs = new ArrayList<>(Collections.nCopies(n+1, 0));
        List<Integer> min_sum = new ArrayList<>(Collections.nCopies(n+1, 0));

        for (int i = 2; i < n+1; i++) {
            int tmp = 0;
            if (nums.get(i-1) - nums.get(i-2) <= d) {
                tmp += 1;
            }

            if (pairs.get(i-2) + tmp > pairs.get(i-1)) {
                pairs.set(i, pairs.get(i-2) + tmp);
                min_sum.set(i, min_sum.get(i-2) + nums.get(i-1) - nums.get(i-2));
            }
            else if (pairs.get(i-2) + tmp < pairs.get(i-1)) {
                pairs.set(i, pairs.get(i-1));
                min_sum.set(i, min_sum.get(i-1));
            }
            else {
                if (tmp == 1) {
                    min_sum.set(i, Math.min(min_sum.get(i-1), min_sum.get(i-2) + nums.get(i-1) - nums.get(i-2)));
                }
                else {
                    min_sum.set(i, Math.min(min_sum.get(i-1), min_sum.get(i-2)));
                }
                pairs.set(i, pairs.get(i-1));
            }
        }

        if (pairs.get(n) == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(min_sum.get(n));
        }
    }
}
