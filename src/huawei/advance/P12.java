package huawei.advance;

import java.util.HashMap; // 导入HashMap类
import java.util.Scanner; // 导入Scanner类
import java.util.Arrays; // 导入Arrays类

public class P12 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in); // 创建Scanner对象
        int cityCount = in.nextInt(); // 城市数量
        int pairCount1 = in.nextInt(); // 可建设高铁的两城市间修建成本列表数量
        int pairCount2 = in.nextInt(); // 必建高铁的城市列表数量

        // 可建设高铁的两城市
        int[][] cityPairs1 = new int[pairCount1][3]; // 创建二维数组
        for (int i = 0; i < pairCount1; i++) {
            cityPairs1[i][0] = in.nextInt(); // 输入城市1
            cityPairs1[i][1] = in.nextInt(); // 输入城市2
            cityPairs1[i][2] = in.nextInt(); // 输入修建成本
        }

        // 必建高铁的两城市
        int[][] cityPairs2 = new int[pairCount2][2]; // 创建二维数组
        for (int i = 0; i < pairCount2; i++) {
            cityPairs2[i][0] = in.nextInt(); // 输入城市1
            cityPairs2[i][1] = in.nextInt(); // 输入城市2
        }

        UF uf = new UF(cityCount); // 创建并查集对象

        // key为修建高铁的两个城市，value为费用
        HashMap<String, Integer> cityMap = new HashMap<>(); // 创建HashMap对象
        for (int[] cityPair : cityPairs1) {
            int city1 = cityPair[0], city2 = cityPair[1];
            cityMap.put(city1 < city2 ? city1 + "-" + city2 : city2 + "-" + city1, cityPair[2]); // 存储城市对应的修建成本
        }

        int result = 0; // 初始化result为0
        // 先计算必建高铁情况下的费用
        for (int[] cityPair : cityPairs2) {
            int city1 = cityPair[0], city2 = cityPair[1];
            result += cityMap.get(city1 < city2 ? city1 + "-" + city2 : city2 + "-" + city1); // 计算修建成本
            // 纳入并查集
            uf.union(city1, city2); // 合并城市
        }

        // 已经满足所有城市通车，直接返回
        if (uf.count == 1) { // 如果只有一个连通分量
            System.out.println(result); // 输出result
            return; // 结束程序
        }

        // 按修建费用排序
        Arrays.sort(cityPairs1, (a, b) -> a[2] - b[2]); // 对城市对应的修建成本进行排序

        for (int[] cityPair : cityPairs1) {
            int city1 = cityPair[0], city2 = cityPair[1];
            // 判断两城市是否相连
            if (uf.item[city1] != uf.item[city2]) {
                uf.union(city1, city2); // 合并城市
                // 若可以合入，则将对应的建造成本计入result
                result += cityPair[2]; // 计算修建成本
            }
            if (uf.count == 1) { // 如果只有一个连通分量
                break; // 结束循环
            }
        }

        // count > 1代表有的城市无法通车
        if (uf.count > 1) { // 如果有多个连通分量
            System.out.println(-1); // 输出-1
            return; // 结束程序
        }

        System.out.println(result); // 输出result
    }
}

// 并查集
class UF {
    int[] item; // 存储并查集的数组
    int count; // 连通分量的数量

    public UF(int n) { // 构造函数
        item = new int[n + 1]; // 初始化数组
        count = n; // 初始化连通分量数量
        for (int i = 0; i < n; i++)
            item[i] = i; // 初始化每个城市所在的连通分量
    }

    public int find(int x) { // 查找x所在的连通分量
        if (x != item[x]) { // 如果x不是根节点
            return (item[x] = find(item[x])); // 路径压缩，将x的父节点设置为根节点
        }
        return x; // 如果x是根节点，直接返回
    }

    public void union(int x, int y) { // 合并x和y所在的连通分量
        int xItem = find(x); // 查找x所在的连通分量
        int yItem = find(y); // 查找y所在的连通分量

        if (xItem != yItem) { // 如果x和y不在同一个连通分量中
            item[yItem] = xItem; // 将y所在的连通分量的根节点设置为x所在的连通分量的根节点
            count--; // 连通分量数量减1
        }
    }
}
