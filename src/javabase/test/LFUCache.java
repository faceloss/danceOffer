package javabase.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LFUCache {
    class Node{
        int key;
        int val;
        int fre;
        public Node(int key, int val, int fre){
            this.key = key;
            this.val = val;
            this.fre = fre;
        }
    }
    int capacity;
    int minFre;
    Map<Integer, Node> map;// 节点数据
    Map<Integer, LinkedList<Node>> maplist;
    public LFUCache(int capacity) {
        this.minFre = 0;
        this.capacity = capacity;
        map = new HashMap<>();
        maplist = new HashMap<>();
    }
    public int get(int key) {
        Node node = map.get(key);
        if(capacity == 0 || node == null){
            return -1;
        }
        // 如果不是-1
        int fre = node.fre, val = node.val;
        maplist.get(fre).remove(node);
        //空的话会删除掉并更新minminFre
        if(maplist.get(fre).size() == 0){
            maplist.remove(fre);
            if(minFre == fre){
                minFre++;
            }
        }
        LinkedList<Node> list =  maplist.getOrDefault(fre+1, new LinkedList<>());
        Node newNode = new Node(key, val, fre+1);
        list.offerFirst(newNode);// fre变了
        map.put(key, newNode);// 频率的变化会改变 fre和值表
        maplist.put(fre+1 , list);
        return val;
    }

    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }
        Node node = map.get(key);
        if(node != null){
            int fre = node.fre, val = node.val;
            Node newNode = new Node(key, value, fre+1);
            maplist.get(fre).remove(node);
            if(maplist.get(fre).size() == 0){
                maplist.remove(fre);
                if(minFre == fre){
                    minFre++;
                }
            }
            LinkedList<Node> list = maplist.getOrDefault(fre+1, new LinkedList<>());
            list.offerFirst(newNode);
            map.put(key, newNode);
            maplist.put(fre+1, list);
        }else{
            // 先删除再添加 不然无法定位fre
            if(map.size() == capacity){
                Node tail = maplist.get(minFre).peekLast();
                map.remove(tail.key);
                maplist.get(minFre).pollLast();
                if(maplist.get(minFre).size() == 0){
                    maplist.remove(minFre);
                }
            }
            LinkedList<Node> list = maplist.getOrDefault(key, new LinkedList<>());
            Node newNode = new Node(key, value, 1);
            list.offerFirst(newNode);
            map.put(key, newNode);
            maplist.put(1, list);
            minFre = 1;//插入新值 因为==null 所以是第一次
        }

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */