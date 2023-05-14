package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/

import java.util.Scanner;
import java.util.Vector;

public class P31 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入一个带空格的字符串
        String inputStr = scanner.nextLine();

        // 将字符串按空格分割成整数数组
        Vector<Integer> nums = new Vector<>();
        while (inputStr.contains(" ")) {
            int found = inputStr.indexOf(" ");
            nums.add(Integer.parseInt(inputStr.substring(0, found)));
            inputStr = inputStr.substring(found + 1);
        }
        nums.add(Integer.parseInt(inputStr));

        // 特判：数组长度为1，输出0
        if (nums.size() == 1) {
            System.out.println(0);
            return;
        }

        // 计算左右乘积子数组
        Vector<Long> leftProduct = new Vector<>(nums.size() + 1);
        leftProduct.add(1L);
        for (int i = 1; i <= nums.size(); i++) {
            leftProduct.add(leftProduct.get(i - 1) * nums.get(i - 1));
        }

        Vector<Long> rightProduct = new Vector<>(nums.size() + 1);
        rightProduct.add(1L);
        for (int i = nums.size() - 1; i >= 0; i--) {
            rightProduct.add(0, rightProduct.get(0) * nums.get(i));
        }

        // 找到左右乘积相等的位置
        int flag = 0;
        for (int i = 1; i < nums.size(); i++) {
            if (leftProduct.get(i).equals(rightProduct.get(i + 1))) {
                flag = 1;
                System.out.println(i);
                break;
            }
        }

        // 不存在中心位置
        if (flag == 0) {
            System.out.println(-1);
        }
    }
}

