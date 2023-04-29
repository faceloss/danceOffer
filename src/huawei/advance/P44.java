package huawei.advance;

import java.util.*;

public class P44 {
    // 深度优先遍历求解最短路径
    public static void dfs(int n, boolean[] visited, List<Integer> path, int[] ans, int[][] distance) {
        // 如果已经遍历完所有基站，更新最短距离
        if (path.size() == n - 1) {
            int dis = distance[0][path.get(0)];
            for (int i = 0; i < path.size() - 1; i++) {
                int p = path.get(i);
                int c = path.get(i + 1);
                dis += distance[p][c];
            }
            dis += distance[path.get(path.size() - 1)][0];
            ans[0] = Math.min(ans[0], dis);
            return;
        }

        // 遍历每个基站
        for (int i = 1; i < n; i++) {
            if (!visited[i]) {
                path.add(i);
                visited[i] = true;
                dfs(n, visited, path, ans, distance);
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] distance = new int[10][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = scanner.nextInt();
            }
        }

        boolean[] visited = new boolean[10];
        Arrays.fill(visited, false);
        List<Integer> path = new ArrayList<>();
        int[] ans = {Integer.MAX_VALUE};

        dfs(n, visited, path, ans, distance);

        System.out.println(ans[0]);
    }
}
