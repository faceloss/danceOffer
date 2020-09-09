package acwing_leetcode.day01;

/**
 * @program: danceOffer
 * @description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * @author: mobing_yin
 * @create: 2020-08-26 10:32
 **/


public class Lc2_AddTwoNumbers {
      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

    public static void main(String[] args) {
        ListNode l0 = new ListNode(9);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(9);

        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        l5.next=l6;
        l6.next=l7;
        l7.next=l8;
        l8.next=l9;
        l9.next=l10;

        ListNode res = addTwoSum2(l0,l1);
        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }
    // 算法思路：两个链子一起走，如果到了null 则继续为null 两者和>10会进位，当前位val%10 进位val/10
    // 假如是 2 4 3 和 5 6 4，产生进位怎么办 7 0 8
    public static ListNode addTwoSum2(ListNode l1, ListNode l2) {
        // 假如是 5 5，假如是 1 2,假如是51和59 011
        ListNode head = new ListNode(-1);
        ListNode ceil = head;
        int value = 0;//表示进位
        while(l1!=null || l2!=null || value>0){
            value = (l1==null?0:l1.val) + (l2==null?0:l2.val) + value;
            int curValue = value%10;
            ListNode curNode = new ListNode(curValue);
            value /= 10;
            ceil.next = curNode;
            ceil = ceil.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        return head.next;
    }
}
