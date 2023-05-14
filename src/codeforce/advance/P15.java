package codeforce.advance;

import java.util.Scanner;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P15 {
    public static void main(String[] args) {
        // 读入输入
        Scanner scanner = new Scanner(System.in);
        int numOfRanges = scanner.nextInt();
        int[][] ranges = new int[numOfRanges][3];
        for (int i = 0; i < numOfRanges; i++) {
            ranges[i][0] = scanner.nextInt();
            ranges[i][1] = scanner.nextInt();
            ranges[i][2] = scanner.nextInt();
        }

        // 输出结果
        System.out.println(minMeetingRooms(ranges));
    }

    public static int minMeetingRooms(int[][] ranges) {
        // 按照会议开始时间排序
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        // 用小根堆维护当前正在开的会议
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int maxAttendees = 0;
        int currentAttendees = 0;
        for (int i = 0; i < ranges.length; i++) {
            // 将已经结束的会议从小根堆中移除，并更新当前参加会议的人数
            while (!pq.isEmpty() && pq.peek()[0] < ranges[i][0]) {
                int[] top = pq.poll();
                currentAttendees -= top[1];
            }

            // 将当前会议加入小根堆，并更新当前参加会议的人数
            pq.offer(new int[]{ranges[i][1], ranges[i][2]});
            currentAttendees += ranges[i][2];

            // 更新最多参加会议的人数
            maxAttendees = Math.max(maxAttendees, currentAttendees);
        }

        return maxAttendees;
    }
}
