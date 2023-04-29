package huawei.advance;

import java.util.*;

public class P33 {
    public static int calculateDays(int efficiency, List<Integer> fields) {
        int days = 0;
        for (int i = 0; i < fields.size(); i++) {
            days += Math.ceil(fields.get(i) / (double)efficiency);
        }
        return days;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numFields = scanner.nextInt();
        int targetDays = scanner.nextInt();

        List<Integer> fields = new ArrayList<>();
        for (int i = 0; i < numFields; i++) {
            int size = scanner.nextInt();
            fields.add(size);
        }

        if (targetDays < numFields) { // 如果目标天数小于果林数，无法完成任务
            System.out.println(-1);
            return;
        }

        Collections.sort(fields); // 对果林大小进行排序
        int minEfficiency = 0, maxEfficiency = fields.get(numFields - 1); // 最小效能为1，最大效能为最大果林大小

        int result = -1;
        while (minEfficiency + 1 < maxEfficiency) {
            int efficiency = (minEfficiency + maxEfficiency) / 2; // 取中间值作为效能
            int days = calculateDays(efficiency, fields); // 计算以该效能完成任务需要的天数

            if (days > targetDays) { // 如果需要的天数比目标天数多，说明效能太低
                minEfficiency = efficiency;
            } else { // 否则效能可能过高或正好
                result = efficiency;
                maxEfficiency = efficiency;
            }
        }
        System.out.println(result);
    }
}
