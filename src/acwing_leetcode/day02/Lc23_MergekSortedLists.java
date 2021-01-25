package acwing_leetcode.day02;

import acwing_leetcode.ListNode;
import acwing_leetcode.day01.Lc2_AddTwoNumbers;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: danceOffer
 * @description:23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * @author: mobing_yin
 * @create: 2020-09-08 17:35
 **/

public class Lc23_MergekSortedLists {
        class Status implements Comparable<Status> {
            int val;
            ListNode ptr;

            Status(int val, ListNode ptr) {
                this.val = val;
                this.ptr = ptr;
            }

            public int compareTo(Status status2) {
                return this.val - status2.val;
            }
        }

        PriorityQueue<Status> queue = new PriorityQueue<Status>();

        public ListNode mergeKLists(ListNode[] lists) {
            for (ListNode node: lists) {
                if (node != null) {
                    queue.offer(new Status(node.val, node));
                }
            }
            ListNode head = new ListNode(0);
            ListNode tail = head;
            while (!queue.isEmpty()) {
                Status f = queue.poll();
                tail.next = f.ptr;
                tail = tail.next;
                if (f.ptr.next != null) {
                    queue.offer(new Status(f.ptr.next.val, f.ptr.next));
                }
            }
            return head.next;
        }

        // 小堆，对k个链表排序
        public ListNode mergeKLists1(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return  o1.val - o2.val;
                }
            });
            ListNode dummy = new ListNode(0);
            ListNode p = dummy;
            for (ListNode node : lists) {
                if (node != null) queue.add(node);
            }
            while (!queue.isEmpty()) {
                p.next = queue.poll();
                p = p.next;
                if (p.next != null) queue.add(p.next);
            }
            return dummy.next;
        }
}
