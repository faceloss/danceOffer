package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/



import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class P43 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读入链表头结点
        String headAddress = scanner.next();

        // 读入链表结点数
        int nodeCount = scanner.nextInt();

        // 读入链表结点信息，并存储到哈希表中
        HashMap<String, String[]> nodeMap = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            String address = scanner.next();
            String value = scanner.next();
            String nextAddress = scanner.next();
            nodeMap.put(address, new String[] {value, nextAddress});
        }

        // 计算链表中间结点的值，并输出
        String midValue = getLinkedListMidValue(headAddress, nodeMap);
        System.out.println(midValue);
    }

    /**
     * 计算链表的中间结点的值
     *
     * @param headAddress 链表的头结点地址
     * @param nodeMap     存储链表结点信息的哈希表
     * @return 链表的中间结点的值
     */
    public static String getLinkedListMidValue(String headAddress, HashMap<String, String[]> nodeMap) {
        LinkedList<String> valueList = new LinkedList<>();

        String[] currentNode = nodeMap.get(headAddress);
        while (currentNode != null) {
            String value = currentNode[0];
            String nextAddress = currentNode[1];

            valueList.add(value);
            currentNode = nodeMap.get(nextAddress);
        }

        int length = valueList.size();
        int midIndex = length / 2;
        return valueList.get(midIndex);
    }
}

