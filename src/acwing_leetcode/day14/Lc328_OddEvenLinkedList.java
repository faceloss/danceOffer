package acwing_leetcode.day14;

import acwing_leetcode.ListNode;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:25
 * @Description:328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class Lc328_OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode headNext = head.next;
        ListNode pre = head;//偶数
        ListNode cur = headNext;//奇数
        ListNode tmp, next;
        // 0 1 null null
        while(pre!=null || cur!=null){
            tmp = cur.next;
            next = cur.next == null ? null : cur.next.next;
            pre.next = tmp;
            cur.next = next;
            pre = tmp;
            cur = next;
        }
        if(pre!=null){
            pre.next = headNext;
        }
        if(cur!=null){
            cur.next = head;
        }
        return dumy.next;
    }
}
