package huawei.advance;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class P07 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 获取输入的字符串
    String numStr = sc.next();

    // 输出获取到的最大数
    System.out.println(getMaxNum(numStr));
  }

  public static String getMaxNum(String numStr) {
    // 用于存储未使用的数字及其数量
    HashMap<Character, Integer> unused = new HashMap<>();
    // 用于存储已经使用过的数字及其数量
    HashMap<Character, Integer> reserve = new HashMap<>();

    // 遍历输入的字符串，将未使用的数字及其数量存储到 unused 中，
    // 已经使用过的数字存储到 reserve 中
    for (int i = 0; i < numStr.length(); i++) {
      char c = numStr.charAt(i);
      unused.put(c, unused.getOrDefault(c, 0) + 1);
      reserve.putIfAbsent(c, 0);
    }

    // 用于存储最终的结果
    LinkedList<Character> stack = new LinkedList<>();

    // 遍历输入的字符串，根据贪心策略将数字添加到 stack 中
    for (int i = 0; i < numStr.length(); i++) {
      char c = numStr.charAt(i);

      // 如果数字已经被使用了两次，则不再添加
      if (reserve.get(c) == 2) {
        unused.put(c, unused.get(c) - 1);
        continue;
      }

      // 如果 stack 中已经有数字，并且栈顶数字小于当前数字，
      // 则将栈顶数字弹出，直到栈顶数字不小于当前数字或者栈为空
      while (stack.size() > 0) {
        char top = stack.getLast();

        if (top < c && unused.get(top) + reserve.get(top) - 1 >= 2) {
          stack.removeLast();
          reserve.put(top, reserve.get(top) - 1);
        } else {
          break;
        }
      }

      // 将当前数字添加到 stack 中，并更新 unused 和 reserve 中的数字数量
      stack.add(c);
      unused.put(c, unused.get(c) - 1);
      reserve.put(c, reserve.get(c) + 1);
    }

    // 将 stack 中的数字转换为字符串并返回
    StringBuilder sb = new StringBuilder();
    for (Character c : stack) {
      sb.append(c);
    }
    return sb.toString();
  }
}
