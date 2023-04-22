package java_base.test;

import acwing_leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class TestFinal {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        Comparator<ListNode> com = new Comparator<ListNode>(){
            public int compare(ListNode v1, ListNode v2) {
                return v1.val - v2.val;
            }
        };
        PriorityQueue<ListNode> queue = new PriorityQueue(com);
        for(int i = 0 ; i<lists.length; i++) {
            if(lists[i] != null) {
                queue.offer(lists[i]);
            }
        }
        ListNode dumy = new ListNode(-1);
        ListNode p = dumy;
        while(!queue.isEmpty()){
            ListNode nex = queue.poll();
            p.next = nex;
            p = p.next;
            if(nex.next != null){
                queue.offer(nex.next);
            }
        }
        return dumy.next;
    }
}