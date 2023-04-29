package huawei.advance;

import java.util.*;

public class P31_TreeBuilder {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 读取输入的字符串
    String input = scanner.nextLine();

    // 解析操作序列
    Integer[][] operations = parseOperations(input);

    // 构建树并返回结果
    String result = buildTree(operations);

    // 输出结果
    System.out.println(result);
  }

  /**
   * 解析操作序列
   */
  private static Integer[][] parseOperations(String input) {
    // 创建 Scanner 对象
    Scanner scanner = new Scanner(input);

    // 读取操作序列字符串
    String str = scanner.nextLine();

    // 解析字符串并返回操作序列
    return Arrays.stream(str.substring(1, str.length() - 1).split("(?<=]), (?=\\[)"))
            .map(s -> Arrays.stream(s.substring(1, s.length() - 1).split(", "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new))
            .toArray(Integer[][]::new);
  }

  /**
   * 构建树并返回结果
   */
  private static String buildTree(Integer[][] operations) {
    // 创建根节点
    Node root = new Node(-1);

    // 创建树的第 0 层
    ArrayList<Node> level0 = new ArrayList<>();
    level0.add(root);

    // 创建树
    ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    tree.add(level0);

    // 遍历操作序列，逐步构建树
    for (int i = 0; i < operations.length; i++) {
      int height = operations[i][0];
      int index = operations[i][1];

      // 如果当前高度还没有节点，则创建新的一层
      if (tree.size() <= height + 1) {
        tree.add(new ArrayList<>());
      }

      // 创建新节点
      Node node = new Node(i);

      // 添加新节点到树中
      Node parent = tree.get(height).get(index);
      if (parent.lc == null || parent.rc == null) {
        tree.get(height + 1).add(node);
      }
      if (parent.lc == null) parent.lc = node;
      else if (parent.rc == null) parent.rc = node;
    }

    // 层序遍历树并返回结果
    LinkedList<Integer> result = new LinkedList<>();
    LinkedList<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      Node node = queue.removeFirst();
      if (node != null) {
        result.add(node.val);
        queue.add(node.lc);
        queue.add(node.rc);
      } else {
        result.add(null);
      }
    }
    while (result.getLast() == null) {
      result.removeLast();
    }
    StringJoiner sj = new StringJoiner(", ", "[", "]");
    for (Integer value : result) {
      sj.add(value + "");
    }
    return sj.toString();
  }
}

/**
 * 树的节点类
 */
class Node {
  int val;
  Node lc;
  Node rc;

  public Node(int val) {
    this.val = val;
    this.lc = null;
    this.rc = null;
  }
}
