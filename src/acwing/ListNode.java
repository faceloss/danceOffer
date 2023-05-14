package acwing;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2020-09-08 17:38
 **/

public class ListNode {
      public ListNode next;
      public int val;
      ListNode() {}
      public ListNode(int val) {
            this.val = val;
            this.next = null;
      }
      ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
      }
}
