package codeforce.advance;

import java.util.*;

public class P03 {
    // 重复操作
    public static void repeatOperation(List<String> stack, int pos, int repeatCount) {
        int count = stack.size() - pos;

        // tempStack用于存储弹栈数据
        List<String> tempStack = new ArrayList<>(Collections.nCopies(count, ""));

        while (count >= 1) {
            count -= 1;
            tempStack.set(count, stack.get(stack.size()-1));
            stack.remove(stack.size()-1);
        }

        String tempStr = "";
        for (String x : tempStack) {
            tempStr += x;
        }
        String result = "";
        //重复repeatCount次
        for (int i = 0; i < repeatCount; i++) {
            result+=tempStr;
        }

        stack.add(result);
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        inputStr = inputStr + " ";
        List<String> stack = new ArrayList<>(); // 栈
        List<Integer> bracketPos = new ArrayList<>(); // 保存所有花括号出现的位置
        String numberStr = ""; // 保存数字字符串

        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);

            // 数字
            if (c >= '0' && c <= '9') {
                numberStr += c;
                continue;
            }

            // 数字
            if (numberStr.length()>0) {
                int repeatCount = Integer.parseInt(numberStr); // 将数字字符串转为整数
                numberStr = "";
                // 若此时栈顶是 } 字符, 将对应的字母重复repeatCount次
                if ("}".equals(stack.get(stack.size()-1))) {
                    //获取上一个 { 的位置
                    int pos = bracketPos.get(bracketPos.size()-1);
                    bracketPos.remove(bracketPos.size()-1);
                    //删除左右{}
                    stack.remove(pos);
                    stack.remove(stack.size()-1);
                    // 重复{}之间的字母
                    repeatOperation(stack, pos, repeatCount);
                } else {
                    //不是 } 字符, 简单重复栈顶字符对应次即可
                    repeatOperation(stack, stack.size() - 1, repeatCount);
                }
            }

            // { 字符
            if (c == '{') {
                bracketPos.add(stack.size()); // 记录花括号位置
            }

            // 其他字符 (字母 + })
            stack.add(String.valueOf(c));
        }

        // 输出
        String result = "";
        for (String x : stack) {
            result += x;
        }
        System.out.println(result);
    }
}
