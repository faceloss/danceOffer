package huawei.advance;

import java.util.Arrays;
import java.util.Scanner;
 
public class P39 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象
 
    int sum = scanner.nextInt(); // 输入总球数
    int n = scanner.nextInt(); // 输入桶的数量
 
    Integer[] buckets = new Integer[n]; // 创建桶数组
    for (int i = 0; i < n; i++) {
      buckets[i] = scanner.nextInt(); // 输入每个桶的球数
    }
 
    System.out.println(getResult(sum, buckets, n)); // 输出结果
  }
 
  public static String getResult(int sum, Integer[] buckets, int n) {
    int total = Arrays.stream(buckets).reduce((p, c) -> p + c).get(); // 计算桶中球的总数
    if (total <= sum) return "[]"; // 如果总球数大于等于桶中球的总数，直接返回空数组
 
    int maxCapacity = Arrays.stream(buckets).max((a, b) -> a - b).get(); // 计算桶中最大容量
    int minCapacity = sum / n; // 计算每个桶的最小容量
 
    final int minCapacityCopy = minCapacity; // 复制最小容量，用于 lambda 表达式中访问
 
    Integer[] ans = Arrays.stream(buckets) // 使用流处理桶数组
        .map(count -> count > minCapacityCopy ? count - minCapacityCopy : 0) // 计算每个桶需要移除的球的数量
        .toArray(Integer[]::new); // 转换为数组
 
    while (maxCapacity - minCapacity > 1) { // 二分查找最大容量
      int midCapacity = (maxCapacity + minCapacity) / 2; // 计算中间容量
 
      Integer[] tmp = new Integer[n]; // 创建临时数组
      int remain = total; // 剩余球数初始化为总球数
      for (int i = 0; i < buckets.length; i++) {
        int removeCount = buckets[i] > midCapacity ? buckets[i] - midCapacity : 0; // 计算需要移除的球的数量
        remain -= removeCount; // 更新剩余球数
        tmp[i] = removeCount; // 将需要移除的球的数量保存到临时数组中
      }
 
      if (remain > sum) { // 如果剩余球数大于总球数，说明容量不足，将最大容量更新为中间容量
        maxCapacity = midCapacity;
      } else if (remain < sum) { // 如果剩余球数小于总球数，说明容量过大，将最小容量更新为中间容量，并保存临时数组
        minCapacity = midCapacity;
        ans = tmp;
      } else { // 如果剩余球数等于总球数，说明已经找到最优解，直接返回临时数组
        ans = tmp;
        break;
      }
    }
 
    return Arrays.toString(ans); // 将结果数组转换为字符串并返回
  }
}
