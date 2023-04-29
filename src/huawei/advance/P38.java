package huawei.advance;

import java.util.Scanner;

public class P38 {
    static class UnionFind {
        int[] parent; // 记录每个节点的父节点
        int count; // 记录连通分量的个数

        public UnionFind(int n) { // 构造函数，初始化parent数组和count
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // 初始时每个节点的父节点是它自己
            }
            count = n;
        }

        public int find(int x) { // 查找x所在的连通分量的代表
            if (x != parent[x]) { // 如果x不是它所在连通分量的代表
                parent[x] = find(parent[x]); // 将x的父节点更新为它所在连通分量的代表
            }
            return parent[x]; // 返回x所在连通分量的代表
        }

        public void unionNodes(int x, int y) { // 合并x和y所在的连通分量
            int rootX = find(x), rootY = find(y); // 找到x和y所在连通分量的代表
            if (rootX != rootY) { // 如果x和y不在同一个连通分量中
                parent[rootX] = rootY; // 将x所在连通分量的代表的父节点更新为y所在连通分量的代表
                count--; // 连通分量的个数减1
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] sites = new int[n][n]; // 定义一个n*n的矩阵sites
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = scanner.nextInt(); // 输入矩阵sites
            }
        }

        UnionFind uf = new UnionFind(n); // 定义一个并查集，初始时有n个节点
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // 遍历上三角矩阵
                if (sites[i][j] == 1) { // 如果i和j可达，即sites[i][j]为1
                    uf.unionNodes(i, j); // 合并i和j所在的连通分量
                }
            }
        }

        System.out.println(uf.count); // 输出连通分量的个数
    }
}
