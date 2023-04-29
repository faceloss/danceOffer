package huawei.advance;

import java.util.Arrays;
import java.util.PriorityQueue; 
import java.util.Scanner; 

public class P29 { // 定义 Main 类
  public static void main(String[] args) { // 主函数
    Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象，从控制台读入数据

    int n = scanner.nextInt(); // 读入工单数量

    int[][] workOrders = new int[n][2]; // 创建工单数组
    for (int i = 0; i < n; i++) { // 循环读入每个工单的 SLA 和积分
      workOrders[i][0] = scanner.nextInt(); // 读入 SLA
      workOrders[i][1] = scanner.nextInt(); // 读入积分
    }

    System.out.println(getMaxScore(n, workOrders)); // 调用 getMaxScore 方法，输出最大积分
  }

  /**
   * @param n 工单数量
   * @param workOrders 工单的 [SLA, 积分]
   * @return 可以获得的最大积分
   */
  public static int getMaxScore(int n, int[][] workOrders) { // 定义 getMaxScore 方法，返回最大积分
    Arrays.sort(workOrders, (a, b) -> a[0] - b[0]); // 按 SLA 升序排序
    PriorityQueue<Integer> scoreQueue = new PriorityQueue<>((a, b) -> a - b); // 创建积分小根堆

    int curTime = 0; // 当前时间
    int maxScore = 0; // 最大积分
    for (int[] workOrder : workOrders) { // 循环处理每个工单
      int endTime = workOrder[0]; // 工单结束时间
      int score = workOrder[1]; // 工单积分

      if (endTime >= curTime + 1) { // 当前工单的 SLA 未超时
        scoreQueue.offer(score); // 将工单积分加入小根堆
        maxScore += score; // 更新最大积分
        curTime++; // 当前时间加 1
      } else { // 当前工单的 SLA 已经超时
        if (scoreQueue.size() == 0) { // 积分小根堆为空，无法替换积分更小的工单
          continue; // 跳过当前工单
        }

        int minScore = scoreQueue.peek(); // 获取积分小根堆堆顶元素
        if (score > minScore) { // 当前工单积分比小根堆堆顶积分高
          scoreQueue.poll(); // 弹出积分小根堆堆顶元素
          scoreQueue.offer(score); // 将当前工单积分加入小根堆
          maxScore += score - minScore; // 更新最大积分
        }
      }
    }

    return maxScore; // 返回最大积分
  }
}
