package aim2offer;

/**
 * 定义一个函数，输入一个链表的头结点，
 * 反转该链表并输出反转后链表的头结点
 */

// 具体同No5
public class No16 {
    public ListNode reverse(ListNode head){
        if(head == null || head.nextNode==null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.nextNode;
        while(cur != null){
            ListNode temp = cur.nextNode;
            cur.nextNode = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
