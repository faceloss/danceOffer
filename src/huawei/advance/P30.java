package huawei.advance;

import java.util.Scanner;

public class P30 {
    public static String[][] sheet;

    // 给每一行增加注释
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入行数和列数
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();

        // 读取表格数据
        sheet = new String[row][col];
        for (int i = 0; i < row; i++) {
            sheet[i] = sc.nextLine().split(" ");
        }

        // 读取计算区域
        String[] nums = sc.nextLine().split(":");
        int[] start = toCoordinate(nums[0]);
        int[] end = toCoordinate(nums[1]);

        // 计算表达式的值
        int sum = 0;
        for (int i = start[0]; i <= end[0]; i++) {
            for (int j = start[1]; j <= end[1]; j++) {
                String temp = sheet[i][j];
                if (temp.contains("=")) {
                    sum += evaluate(temp);
                } else {
                    sum += Integer.valueOf(temp);
                }
            }
        }

        System.out.println(sum);
    }

    // 计算表达式的值
    public static int evaluate(String s) {
        s = s.replace("=", "");
        boolean isAddition = true; // 是否为加法运算
        boolean isDigit = true; // 是否为纯数字
        int num1 = 0;
        int num2 = 0;
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-' || c == '+') {
                if (c == '-') {
                    isAddition = false;
                }
                if (isDigit) { // 纯数字
                    num1 = Integer.valueOf(temp);
                } else {
                    int[] coordinate = toCoordinate(temp); // 先求出其坐标位置
                    String str = sheet[coordinate[0]][coordinate[1]];
                    if (str.contains("=")) { // 如果此坐标位置还是一个算式需要继续求值
                        num1 = evaluate(str);
                    } else {
                        num1 = Integer.valueOf(str);
                    }
                }
                temp = "";
                isDigit = true;
            } else {
                if (Character.isLetter(c)) {
                    isDigit = false; // 包含字母则非纯数字
                }
                temp += c;
            }
            if (i == s.length() - 1) {
                if (isDigit) { // 纯数字
                    num2 = Integer.valueOf(temp);
                } else {
                    int[] coordinate = toCoordinate(temp); // 先求出其坐标位置
                    String str = sheet[coordinate[0]][coordinate[1]];
                    if (str.contains("=")) { // 如果此坐标位置还是一个算式需要继续求值
                        num2 = evaluate(str);
                    } else {
                        num2 = Integer.valueOf(str);
                    }
                }
            }
        }
        return isAddition ? num1 + num2 : num1 - num2;
    }

    // 将坐标字符串转换为坐标数组
    public static int[] toCoordinate(String s) {
        int y = s.charAt(0) - 'A';
        String num = "";
        for (int i = 1; i < s.length(); i++) {
            num += s.charAt(i);
        }
        return new int[]{Integer.valueOf(num) - 1, y};
    }
}
