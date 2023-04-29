package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.*;

public class P58 {
    // 分割字符串函数，将字符串按照空格分割成整数数组
    public static List<Integer> split(String str) {
        List<Integer> nums = new ArrayList<>();
        // 使用while循环和indexOf函数分割字符串
        int pos = 0;
        while (pos < str.length()) {
            int found = str.indexOf(' ', pos);
            if (found == -1) {
                found = str.length();
            }
            int num = Integer.parseInt(str.substring(pos, found));  // 将字符串转换成整数
            nums.add(num);
            pos = found + 1;
        }
        return nums;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input_str = sc.nextLine();
        List<Integer> seats = split(input_str);

        int result = 0, left = 0, right = 0;
        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i) == 1) {  // 如果当前位置有人
                left++;  // 左边连续老员工数加1
            }
            else if (seats.get(i) == 2) {  // 如果当前位置是障碍物
                left = 0;  // 左边连续老员工数清零
            }
            else if (seats.get(i) == 0) {  // 如果当前位置是空置
                for (int j = i + 1; j < seats.size(); j++) {  // 从当前位置往右遍历
                    if (seats.get(j) == 1) {  // 如果右边有老员工
                        right++;  // 右边连续老员工数加1
                    }
                    else if (seats.get(j) == 0 || seats.get(j) == 2) {  // 如果右边是空置或障碍物
                        break;  // 停止遍历
                    }
                }
                if (result < left + right) {  // 如果当前空置位置的友好度更高
                    result = left + right;  // 更新最大友好度
                }
                right = 0;  // 右边连续老员工数清零
                left = 0;  // 左边连续老员工数清零
            }
        }
        System.out.println(result);  // 输出所有空位中友好度的最大值
    }
}

