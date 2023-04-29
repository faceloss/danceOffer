package huawei.advance;

import java.util.*;

public class P32 {
    // 深度优先搜索，index表示从数组的第几个元素开始搜索，sum表示当前已经选取的元素的和，min表示数组中最小的元素
    // m表示要组成的和，count表示组装办法的数量
    public static int dfs(List<Integer> arr, int index, int sum, int min, int m, int count) {
        if (sum > m) { // 如果当前已选取的元素和大于m，返回当前组装办法的数量
            return count;
        }
        if (sum == m || (m - sum < min && m - sum > 0)) { // 如果当前已选取的元素和等于m或者m减去当前已选取的元素和小于最小元素且大于0，返回当前组装办法的数量+1
            return count + 1;
        }
        for (int i = index; i < arr.size(); i++) { // 从index开始搜索
            count = dfs(arr, i, sum + arr.get(i), min, m, count); // 递归搜索下一个元素
        }
        return count;
    }

    public static int getResult(List<Integer> arr, int m) {
        arr.removeIf(val -> val > m); // 只保留比m小的连续整数
        int min = arr.get(0);
        return dfs(arr, 0, 0, min, m, 0); // 从第一个元素开始搜索
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) { // 逐行读入输入
            String line = scanner.nextLine();
            lines.add(line);
            if (lines.size() == 2) { // 如果读入了2行，说明输入结束，开始处理数据
                List<Integer> arr = new ArrayList<>();
                String s = lines.get(0);
                int m = Integer.parseInt(lines.get(1));
                String[] nums = s.split(" ");
                for (String num : nums) {
                    arr.add(Integer.parseInt(num));
                }
                System.out.println(getResult(arr, m)); // 输出组装办法的数量
                lines.clear(); // 清空lines，准备读入下一组数据
            }
        }
    }
}
