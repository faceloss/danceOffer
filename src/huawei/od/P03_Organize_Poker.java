package huawei.od;

import java.util.*;
/**
 * 扑克排序：炸弹 葫芦 三张 对子 单张，从大到小，333 222 小于 33322 2
 * */
public class P03_Organize_Poker {
    // 定义函数，用于输出最终结果
    public static String getResult(List<Integer> arr) {
        // 统计数组中每个数字出现的次数
        Map<Integer, Integer> card = new HashMap<>();
        for (int num : arr) {
            if (card.containsKey(num)) {
                int val = card.get(num);
                card.put(num, val + 1);
            } else {
                card.put(num, 1);
            }
        }

        // 定义存储不同组合的容器
        Map<String, List<List<Integer>>> combine = new HashMap<>();
        combine.put("4", new ArrayList<>());
        combine.put("3+2", new ArrayList<>());
        combine.put("3", new ArrayList<>());
        combine.put("2", new ArrayList<>());
        combine.put("1", new ArrayList<>());

        // 将数字按照出现次数分组
        for (Map.Entry<Integer, Integer> entry : card.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            switch (count) {
                case 3:
                    combine.get("3").add(Arrays.asList(num));
                    break;
                case 2:
                    combine.get("2").add(Arrays.asList(num));
                    break;
                case 1:
                    combine.get("1").add(Arrays.asList(num));
                    break;
                default:
                    combine.get("4").add(Arrays.asList(num, count));
            }
        }
        // 对四张相同的牌进行排序
        Collections.sort(combine.get("4"),
                ((o1, o2) -> o2.get(1) != o1.get(1) ? o2.get(1) - o1.get(1) : o2.get(0) - o1.get(0)));

        // 对三张相同的牌进行排序
        Collections.sort(combine.get("3"), ((o1, o2) -> o2.get(0) - o1.get(0)));

        // 对两张相同的牌进行排序
        Collections.sort(combine.get("2"), ((o1, o2) -> o2.get(0) - o1.get(0)));

        // 尝试组合出葫芦
        while (combine.get("3").size() > 0) {
            // 如果没有两张相同的牌或者只剩下一组三张相同的牌，则无法组成葫芦，退出循环
            if (combine.get("2").size() == 0 && combine.get("3").size() == 1) break;

            // 取出一组三张相同的牌
            int san_top = combine.get("3").get(0).get(0);
            combine.get("3").remove(0);

            // 如果还有另一组三张相同的牌，则与之组合成三带二
            int tmp;
            if (combine.get("2").size() == 0 || (combine.get("3").size() > 0
                    && combine.get("3").get(0).get(0) > combine.get("2").get(0).get(0))) {
                tmp = combine.get("3").get(0).get(0);
                combine.get("3").remove(0);
                combine.get("1").add(Arrays.asList(tmp));
            } else {
                tmp = combine.get("2").get(0).get(0);
                combine.get("2").remove(0);
            }
            combine.get("3+2").add(Arrays.asList(san_top, tmp));
        }

        // 对单张牌进行排序
        Collections.sort(combine.get("1"), ((o1, o2) -> o2.get(0) - o1.get(0)));

        // 将不同的组合按照顺序组合成最终的牌型
        List<Integer> ans = new ArrayList<>();
        for (List<Integer> vals : combine.get("4")) {
            int score = vals.get(0);
            int count = vals.get(1);
            for (int i = 0; i < count; i++) {
                ans.add(score);
            }
        }
        for (List<Integer> vals : combine.get("3+2")) {
            int san = vals.get(0);
            int er = vals.get(1);
            for (int i = 0; i < 3; i++) ans.add(san);
            for (int i = 0; i < 2; i++) ans.add(er);
        }
        for (List<Integer> vals : combine.get("3")) {
            for (int i = 0; i < 3; i++) ans.add(vals.get(0));
        }
        for (List<Integer> vals : combine.get("2")) {
            for (int i = 0; i < 2; i++) ans.add(vals.get(0));
        }
        for (List<Integer> vals : combine.get("1")) {
            ans.add(vals.get(0));
        }

        // 将最终结果转换为字符串输出
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            res.append(ans.get(i));
            if (i != ans.size() - 1) res.append(" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        // 将输入字符串转换为数组
        List<Integer> arr = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                arr.add(Integer.parseInt(num.toString()));
                num = new StringBuilder();
            } else {
                num.append(c);
            }
        }
        arr.add(Integer.parseInt(num.toString()));

        String res = getResult(arr);
        System.out.println(res);
    }
}
