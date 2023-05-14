package acwing.day08;

import java.util.*;


public class UnionFindDemo{
    public static void main(String[] args) {
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        ArrayList<UndirectedGraphNode> neighbors1 = new ArrayList<>();
        ArrayList<UndirectedGraphNode> neighbors2 = new ArrayList<>();
        ArrayList<UndirectedGraphNode> neighbors3 = new ArrayList<>();
        neighbors1.add(node2);
        neighbors1.add(node3);
        neighbors2.add(node1);
        neighbors3.add(node1);
        node1.neighbors = neighbors1;
        node2.neighbors = neighbors2;
        node3.neighbors = neighbors3;
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node4);
        UnionFindDemo s = new UnionFindDemo();
        s.connectedSet(nodes);

    }
    static class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
    class UnionFind{ //并查集
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
        UnionFind(HashSet<Integer> hashSet) {
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
    // 这里的nodes节点是图中所有节点 。。。。。
    // 收集所有的点 初始化并查集 将点与相邻的点合并(不是同一个连通块碰不到的) 但是在同一个unionfind中
    // 将并查集中的每一个点查找他的老板添加到老板的名下 各个老板建立起来 员工收集起来
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes){
        HashSet<Integer> hashSet = new HashSet<Integer>(); //建立hashset存出现的node字符
        //循环把无向图里的字符元素都存入hashset中
        for (UndirectedGraphNode node : nodes) {
            hashSet.add(node.label);
            for (UndirectedGraphNode neighbour : node.neighbors) {
                hashSet.add(neighbour.label);
            }
        }
        //用并查集构建连接图 初始化自己指向自己 然后如何合并呢？
        UnionFind uf = new UnionFind(hashSet);
        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbour : node.neighbors) {
                int find_now = uf.find(node.label);
                int find_neighbour = uf.find(neighbour.label);
                if (find_now != find_neighbour) {
                    uf.union(node.label, neighbour.label);
                }
            }
        }
        // hashset中存放着所有点 union存放着关系？
        //查找联通块的节点集合
        HashMap<Integer, List<Integer> > hashMap = new HashMap<Integer, List<Integer> >();
        for (int node : hashSet) {
            int root = uf.find(node);// 找到并查集的老板
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