package codeforce.advance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
 
public class P36 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
 
    int initialSheepCount = sc.nextInt();  // 初始本岸羊的数量
    int initialWolfCount = sc.nextInt();   // 初始本岸狼的数量
    int boatCapacity = sc.nextInt();       // 小船的容量
 
    System.out.println(getResult(initialSheepCount, initialWolfCount, boatCapacity));
  }
 
  /**
   * @param initialSheepCount 初始本岸羊的数量
   * @param initialWolfCount 初始本岸狼的数量
   * @param boatCapacity 小船的容量
   * @return 最少运送次数
   */
  public static int getResult(int initialSheepCount, int initialWolfCount, int boatCapacity) {
    ArrayList<Integer> ans = new ArrayList<>();  // 记录所有可能的运送次数
    dfs(initialSheepCount, initialWolfCount, boatCapacity, 0, 0, 0, ans);  // 深度优先搜索
 
    if (ans.size() > 0) {  // 如果存在可行解
      return Collections.min(ans);  // 返回最小次数
    } else {  // 如果不存在可行解
      return 0;  // 返回0
    }
  }
 
  /**
   * 深度优先搜索
   * @param currentSheepCount 当前本岸羊的数量
   * @param currentWolfCount 当前本岸狼的数量
   * @param boatCapacity 小船的容量
   * @param oppositeSheepCount 对岸羊的数量
   * @param oppositeWolfCount 对岸狼的数量
   * @param currentTransportCount 当前已经运送的次数
   * @param ans 所有可能的运送次数
   */
  public static void dfs(
      int currentSheepCount,
      int currentWolfCount,
      int boatCapacity,
      int oppositeSheepCount,
      int oppositeWolfCount,
      int currentTransportCount,
      ArrayList<Integer> ans) {
    if (currentSheepCount == 0 && currentWolfCount == 0) {  // 如果本岸羊和狼都已经运完
      ans.add(currentTransportCount);  // 记录次数
      return;
    }
 
    if (currentSheepCount + currentWolfCount <= boatCapacity) {  // 如果本岸羊和狼都可以一次性运完
      ans.add(currentTransportCount + 1);  // 记录次数
      return;
    }
 
    for (int i = 0; i <= Math.min(boatCapacity, currentSheepCount); i++) {  // 枚举船上的羊数量
      for (int j = 0; j <= Math.min(boatCapacity, currentWolfCount); j++) {  // 枚举船上的狼数量
        if (i + j == 0) continue;  // 船上没有羊和狼，跳过
        if (i + j > boatCapacity) break;  // 船上羊和狼超过船载，跳过
 
        if (currentSheepCount - i <= currentWolfCount - j && currentSheepCount - i != 0) continue;  // 本岸羊 <= 本岸狼，说明狼运少了，跳过
 
        if (oppositeSheepCount + i <= oppositeWolfCount + j && oppositeSheepCount + i != 0) break;  // 对岸羊 <= 对岸狼，说明狼运多了，跳过
 
        if (oppositeSheepCount + i == 0 && oppositeWolfCount + j >= boatCapacity) break;  // 对岸没羊，但是对岸狼已经超过船载量，即下次即使整船都运羊，也无法保证对岸羊 > 对岸狼，跳过
 
        dfs(currentSheepCount - i, currentWolfCount - j, boatCapacity, oppositeSheepCount + i, oppositeWolfCount + j, currentTransportCount + 1, ans);  // 递归搜索
      }
    }
  }
}
