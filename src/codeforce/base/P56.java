package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Scanner;
import java.util.Vector;

public class P56 {
    public static void main(String[] args) {
        // 处理输入
        Scanner sc = new Scanner(System.in);
        String input_M = sc.nextLine();
        String input_N = sc.nextLine();
        String user_input = sc.nextLine();
        int M = Integer.parseInt(input_M);
        int N = Integer.parseInt(input_N);

        Vector<Double> used = new Vector<Double>(N);
        for (int i = 0; i < N; i++) {
            used.add(M * 1.25);
        }

        for (char c : user_input.toCharArray()) {
            double capacity = 0.0;
            if (c == 'A') {
                capacity = 1.25;
            } else if (c == 'B') {
                capacity = 2.5;
            } else {
                capacity = 10;
            }

            for (int i = 0; i < N; i++) {
                if (used.get(i) >= capacity) {
                    used.set(i, used.get(i) - capacity);
                    break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            String output_str = "";
            for (int j = 0; j < M - used.get(i) / 1.25; j++) {
                output_str += '1';
            }
            for (int j = 0; j < used.get(i) / 1.25; j++) {
                output_str += "0";
            }
            System.out.println(output_str);
        }
    }
}

