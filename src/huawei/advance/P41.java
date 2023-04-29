package huawei.advance;

import java.util.*;

public class P41 {
    public static void main(String[] args) {
        //输入处理
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //人数

        List<List<Integer>> intervals = new ArrayList<>(); //存储区间
        int max_site = 0; //记录最大的场地编号
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            max_site = Math.max(max_site, Math.max(start, end)); //更新最大场地编号
            intervals.add(Arrays.asList(start, end)); //将区间存入List
        }

        List<List<Integer>> sites = new ArrayList<>(); //存储场地
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).get(0) > intervals.get(i).get(1)) { 
                sites.add(Arrays.asList(intervals.get(i).get(0), max_site));
                sites.add(Arrays.asList(1, intervals.get(i).get(1)));
            } else {
                sites.add(Arrays.asList(intervals.get(i).get(0), intervals.get(i).get(1)));
            }
        }

        //创建场地的数据结构
        Map<Integer, Integer> site_map = new HashMap<>();
        for (List<Integer> site : sites) { //遍历所有场地
            for (int i = site.get(0); i <= site.get(1); i++) { //将场地的使用时间加入map中
                site_map.put(i, site_map.getOrDefault(i, 0) + 1);
            }
        }

        //将map信息转到List中，以便后续排序
        List<List<Integer>> site_count = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : site_map.entrySet()) {
            List<Integer> temp = new ArrayList<>();
            temp.add(entry.getKey());
            temp.add(entry.getValue());
            site_count.add(temp);
        }

        //按照场地使用次数排序
        Collections.sort(site_count, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> a, List<Integer> b) {
                //按照第二个元素从大到小排序
                if (a.get(1).equals(b.get(1))) {
                    return a.get(0) - b.get(0);
                }
                return b.get(1) - a.get(1);
            }
        });

        //输出
        System.out.println(site_count.get(0).get(0));
    }
}
