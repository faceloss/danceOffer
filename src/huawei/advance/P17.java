package huawei.advance;

import java.util.LinkedList; // 导入LinkedList类
import java.util.Scanner; // 导入Scanner类

public class P17 {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in); // 创建Scanner对象，从标准输入读取数据

int n = scanner.nextInt(); // 读取n

LinkedList<Integer> numsList = new LinkedList<>(); // 创建LinkedList对象，保存输入的数字
for (int i = 0; i < n; i++) {
numsList.add(scanner.nextInt()); // 读取n个数字，添加到LinkedList中
}

System.out.println(getMaxEqualSumGroupNum(numsList, n)); // 输出最大的相等和子集的个数
}

public static int getMaxEqualSumGroupNum(LinkedList<Integer> numsList, int n) {
numsList.sort((a, b) -> b - a); // 对LinkedList中的数字进行降序排序

int sum = 0;
for (Integer num : numsList) { // 计算LinkedList中所有数字的和
sum += num;
}

while (n >= 1) { // 从大到小枚举相等和子集的个数
LinkedList<Integer> numsListCopy = new LinkedList<>(numsList); // 复制一份LinkedList对象
if (canPartitionMSubsets(numsListCopy, sum, n)) { // 判断是否能将LinkedList分成n个相等和子集
return n; // 如果可以，返回相等和子集的个数
}
n--; // 如果不能，继续枚举更小的相等和子集的个数
}

return 1; // 如果n=1时仍然不能分成相等和子集，说明所有数字的和相等，返回1
}

public static boolean canPartitionMSubsets(LinkedList<Integer> numsList, int sum, int m) {
if (sum % m != 0) { // 如果总和不能被m整除，说明不能将LinkedList分成m个相等和子集
return false;
}

int subSum = sum / m; // 计算每个相等和子集的和

if (subSum < numsList.get(0)) { // 如果最大的数字大于每个子集的和，说明无法分成相等和子集
return false;
}

while (numsList.size() > 0 && numsList.get(0) == subSum) { // 如果LinkedList中有数字等于每个子集的和，将它们从LinkedList中移除，并减少子集的个数m
numsList.removeFirst();
m--;
}

int[] buckets = new int[m]; // 创建长度为m的分组数组，用于保存每个子集的和
return partition(numsList, 0, buckets, subSum); // 判断是否能将LinkedList分成m个相等和子集
}

public static boolean partition(LinkedList<Integer> numsList, int index, int[] buckets, int subSum) {
if (index == numsList.size()) { // 如果遍历完LinkedList中所有数字，说明可以将LinkedList分成m个相等和子集
return true;
}

int select = numsList.get(index); // 获取当前遍历到的数字

for (int i = 0; i < buckets.length; i++) { // 遍历分组数组
if (i > 0 && buckets[i] == buckets[i - 1]) { // 如果当前分组的和与前一个分组的和相等，说明当前数字可以放到前一个分组中，不需要再次计算
continue;
}
if (select + buckets[i] <= subSum) { // 如果当前数字加上当前分组的和小于等于每个子集的和，将当前数字放到当前分组中
buckets[i] += select;
if (partition(numsList, index + 1, buckets, subSum)) { // 继续遍历下一个数字
return true; // 如果可以将LinkedList分成m个相等和子集，返回true
}
buckets[i] -= select; // 如果不能，将当前数字从当前分组中移除，尝试将当前数字放到下一个分组中
}
}

return false; // 如果无法将LinkedList分成m个相等和子集，返回false
}
}
