package codeforce.base;

import java.util.Scanner;
/**
 * @program: danceOffer
 * @description: 机房布局：每一个机柜旁边需要一个电箱，问至少需要多少个电箱MIIM,I是间隔 M是电箱
 * MIIM，需要在间隔上放两个电箱
 * @author: mobing_yin
 * @create: 2023-04-22 17:03
 **/
public class P05_ComputeRoom_Layout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cabinetLayout = scanner.nextLine();
        int length = cabinetLayout.length();
        int answer = 0;
        for (int i = 0; i < length; i++) {
            if (cabinetLayout.charAt(i) == 'M') {
                if (i + 1 < length && cabinetLayout.charAt(i + 1) == 'I') { // 当前机柜右边有间隔
                    answer++; // 在右边放电箱
                    i += 2; // 跳过下一个间隔
                } else if (i - 1 >= 0 && cabinetLayout.charAt(i - 1) == 'I') { // 当前机柜左边有间隔
                    answer++; // 在左边放电箱
                } else { // 无法在机柜边上放置电箱
                    answer = -1;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}


