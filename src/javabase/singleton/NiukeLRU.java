package javabase.singleton;
import java.util.*;
public class NiukeLRU {
    class Catch{
         class DlNode{
            DlNode pre;
            DlNode nex;
            int key;
            int val;
            public DlNode(){}
            public DlNode(int key1, int val1){
                this.key = key1;
                this.val = val1;
            }
        }
        int capacity;
        int size;
        Map<Integer, DlNode> map = new HashMap<>();
        DlNode head;
        DlNode tail;
        public Catch(int capacity){
            this.size = 0;
            this.capacity = capacity;
            head = new DlNode();
            tail = new DlNode();
            head.nex = tail;
            tail.pre = head;
        }
        public int get(int key){
            DlNode node = map.get(key);
            if(node == null){
                return -1;
            }
            moveTohead(node);
            return node.val;
        }
        public void put(int key, int val){
            DlNode node = map.get(key);
            if(node != null){
                moveTohead(node);
                node.val = val;
            }else{
                //新节点
                DlNode newNode = new DlNode(key, val);
                map.put(key, newNode);
                size++;
                addTohead(newNode);
                if(size > capacity){
                    DlNode last = removeTail();
                    size--;
                    map.remove(last.key);
                }
            }
        }
        public void moveTohead(DlNode node){
            remove(node);
            addTohead(node);
        }
        public void addTohead(DlNode node){
            node.pre = head;
            node.nex = head.nex;
            head.nex.pre= node;
            head.nex = node;
        }
        public void remove(DlNode node){
            node.pre.nex = node.nex;
            node.nex.pre = node.pre;
        }
        public DlNode removeTail(){
            DlNode last = tail.pre;
            remove(last);
            return last;
        }

    }
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        int len = (int)Arrays.stream(operators).filter(x -> x[0]==2).count();
        int[] res = new int[len];
        int index = 0;
        Catch catchR  = new Catch(k);
        for(int[] operate : operators){
            if(operate[0] == 2){
                res[index++] = catchR.get(operate[1]);
            }else if(operate[1] == 1){
                catchR.put(operate[1], operate[2]);
            }
        }
        return res;
    }
}
