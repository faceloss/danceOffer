package acwing_leetcode.day01;

/**
 * @Auther: mobing
 * @Date: 2020/9/5 18:04
 * @Description:
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class Lc19_DeleteKthNodeOfList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(null == head &&  n < 0){
            return null;
        }
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        int sum = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            sum++;
        }
        int len = sum - n + 1;
        ListNode pre = dumy;
        ListNode cur = head;
        while(len!=1){
            pre = pre.next;
            cur = cur.next;
            len--;
        }
        // 找到了cur需要删除的点，pre是链表基操dumy
        pre.next = cur.next;
        return dumy.next;
    }
}
