package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class P47 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int monthCount = scanner.nextInt(); // 打卡月份数

        int[] dayCount = new int[30]; // 每个月的天数
        for (int i = 0; i < 30; i++) {
            dayCount[i] = scanner.nextInt();
        }

        int[][] dayIds = new int[30][]; // 每天打卡员工的id
        for (int i = 0; i < 30; i++) {
            int m = dayCount[i];
            dayIds[i] = new int[m];
            for (int j = 0; j < m; j++) {
                dayIds[i][j] = scanner.nextInt();
            }
        }

        System.out.println(getResult(dayIds));
    }

    public static String getResult(int[][] dayIds) {
        HashMap<Integer, Integer[]> employees = new HashMap<>(); // 员工id和打卡信息的映射

        for (int i = 0; i < dayIds.length; i++) {
            int[] ids = dayIds[i];

            for (int id : ids) {
                if (employees.containsKey(id)) {
                    employees.get(id)[0]++; // 打卡次数+1
                } else {
                    // 加入数组含义是：该id员工的 [打卡次数，第一天打卡日期]
                    employees.put(id, new Integer[] {1, i});
                }
            }
        }

        ArrayList<Integer[]> list = new ArrayList<>(); // 存储有打卡记录的员工信息
        for (Integer id : employees.keySet()) {
            Integer[] employee = employees.get(id);
            int count = employee[0]; // 打卡次数
            int firstDay = employee[1]; // 第一天打卡日期
            list.add(new Integer[] {id, count, firstDay});
        }

        // 按规则排序
        list.sort(
                (a, b) ->
                        a[1].equals(b[1]) ? (a[2].equals(b[2]) ? a[0] - b[0] : a[2] - b[2]) : b[1] - a[1]);

        StringJoiner sj = new StringJoiner(" ");
        // 不考虑并列的情况，按规则返回前5名员工的id即可，如果当月打卡的员工少于5个，按规则排序返回所有有打卡记录的员工id
        for (int i = 0; i < Math.min(5, list.size()); i++) {
            sj.add(list.get(i)[0] + "");
        }
        return sj.toString();
    }
}


