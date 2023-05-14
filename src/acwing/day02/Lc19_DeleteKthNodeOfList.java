package acwing.day02;

/**
 * @Auther: mobing
 * @Date: 2020/9/5 18:04
 * @Description:
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class Lc19_DeleteKthNodeOfList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if(head ==null || n<0){
            return null;
        }
        // 倒数第n等于正数len-n+1
        int len = 0;
        ListNode temp = head;
        while(temp != null){
            len++;
            temp = temp.next;
        }
        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        int m = len - n + 1;
        // 如何删除节点 需要三个点  -1 1 2 3 ，其中-1是dumy 如何删除2 当然是让1指向3即可
        ListNode pre = dumy;
        ListNode cur = head;
        //假设删除第二个 即点2
        while(m != 1){
            pre = pre.next;
            cur = cur.next;
            m--;
        }
        pre.next = cur.next;
        //如果是1不用处理
        return dumy.next;
    }
}
