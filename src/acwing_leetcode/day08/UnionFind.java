package acwing_leetcode.day08;

import chengyun_zuo.chapter_1_stackandqueue.Problem_08_MaxTree;

import java.util.*;

public class UnionFind{

    class UnionFind1{ //并查集
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
        UnionFind1(HashSet<Integer> hashSet) {
            //初始化
            for (Integer node : hashSet) {
                father.put(node, node);
            }
        }
        //查找函数
        int find(int x) {
            int root = father.get(x);
            while (root != father.get(root)) {
                root = father.get(root);
            }
            return root;
        }
        //合并函数
        void union(int a, int b) {
            int roota = find(a),rootb = find(b);
            if (roota != rootb) {
                father.put(roota, rootb);
            }
        }
    }
    public List<List<Integer> > connectedSet(List<UndirectedGraphNode> nodes){
        HashSet<Integer> hashSet = new HashSet<Integer>(); //建立hashset存出现的node字符
        //循环把无向图里的字符元素都存入hashset中
        for (UndirectedGraphNode node : nodes) {
            System.out.println(node.label);
            hashSet.add(node.label);
            // for (UndirectedGraphNode neighbour : node.neighbors) {
            //     hashSet.add(neighbour.label);
            // }
        }
        //用并查集构建连接图
        UnionFind1 uf = new UnionFind1(hashSet);
        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbour : node.neighbors) {
                int find_now = uf.find(node.label);
                int find_neighbour = uf.find(neighbour.label);
                if (find_now != find_neighbour) {
                    uf.union(node.label, neighbour.label);
                }
            }
        }
        //查找联通块的节点集合
        HashMap<Integer, List<Integer> > hashMap = new HashMap<Integer, List<Integer> >();
        for (int node : hashSet) {
            int root = uf.find(node);
            if (!hashMap.containsKey(root)) {
                hashMap.put(root, new ArrayList<Integer>());
            }
            List<Integer> now = hashMap.get(root);
            now.add(node);
            hashMap.put(root, now);
        }
        //遍历hashmap中的集合们并排序
        List<List<Integer> > ans = new ArrayList<List<Integer> >();
        for (List<Integer> value : hashMap.values()) {
            Collections.sort(value);
            ans.add(value);
        }
        return ans;
    }
}

class UndirectedGraphNode {
        //无向图 只有节点值和邻居
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) {
         label = x;
          neighbors = new ArrayList<UndirectedGraphNode>();
      }
}
