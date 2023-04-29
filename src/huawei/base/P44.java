package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
class P44 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入
        int numRecords = scanner.nextInt(); // 打卡记录数目
        scanner.nextLine();
        String[][] records = new String[numRecords][];
        for (int i = 0; i < numRecords; i++) {
            records[i] = scanner.nextLine().split(",");
        }

        // 存放每位员工的打卡记录
        HashMap<String, ArrayList<String[]>> recordMap = new HashMap<>();
        TreeSet<Integer> result = new TreeSet<>();

        // 异常规则1：打卡时间不一致
        for (int i = 0; i < records.length; i++) {
            // 在打卡记录后面增加一个索引，方便后面输出时按照输入顺序排序
            String[] singleRecord = Arrays.copyOf(records[i], records[i].length + 1);
            singleRecord[singleRecord.length - 1] = i + "";

            if (!singleRecord[3].equals(singleRecord[4])) {
                result.add(i);
            } else {
                String id = singleRecord[0];
                if (recordMap.containsKey(id)) {
                    recordMap.get(id).add(singleRecord);
                } else {
                    ArrayList<String[]> list = new ArrayList<>();
                    list.add(singleRecord);
                    recordMap.put(id, list);
                }
            }
        }

        // 异常规则2：在60分钟内，两次打卡地点距离大于5米
        for (String id : recordMap.keySet()) {
            ArrayList<String[]> idRecords = recordMap.get(id);

            // 按照打卡时间排序，以便后面双层循环加速
            idRecords.sort((a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));

            for (int i = 0; i < idRecords.size(); i++) {
                int time1 = Integer.parseInt(idRecords.get(i)[1]);
                int dist1 = Integer.parseInt(idRecords.get(i)[2]);

                for (int j = i + 1; j < idRecords.size(); j++) {
                    int time2 = Integer.parseInt(idRecords.get(j)[1]);
                    int dist2 = Integer.parseInt(idRecords.get(j)[2]);

                    // 如果当前的两次打卡时间超过60分钟，后面的肯定也超过60分钟了
                    if (time2 - time1 >= 60) {
                        break;
                    } else {
                        if (Math.abs(dist2 - dist1) > 5) {
                            result.add(Integer.parseInt(idRecords.get(i)[5]));
                            result.add(Integer.parseInt(idRecords.get(j)[5]));
                        }
                    }
                }
            }
        }

        // 输出结果
        if (result.isEmpty()) {
            System.out.println("null");
        } else {
            String resStr = result.stream()
                    .map(index -> join(records[index]))
                    .collect(Collectors.joining(":"));
            System.out.println(resStr);
        }
    }

    // 将字符串数组拼接成一个字符串
    public static String join(String[] strs) {
        String s = "";
        for (String str : strs) {
            s+=str+",";
        }
        return s.substring(0, s.length()-1);

    }
}

