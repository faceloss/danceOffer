package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:23
 **/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

class P69 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfWineshops = scanner.nextInt();
        int numberOfPickedWineshops = scanner.nextInt();
        int referencePrice = scanner.nextInt();

        int[] WineshopPrices = new int[numberOfWineshops];
        for (int i = 0; i < numberOfWineshops; i++) {
            WineshopPrices[i] = scanner.nextInt();
        }
        Arrays.sort(WineshopPrices);

        int[][] priceDifference = new int[numberOfWineshops][2];
        for (int i = 0; i < numberOfWineshops; i++) {
            int price = WineshopPrices[i];
            priceDifference[i][0] = price;
            priceDifference[i][1] = Math.abs(price - referencePrice);
        }

        List<int[]> sortedPriceDifference = Arrays.stream(priceDifference)
                .sorted(Comparator.comparingInt(Wineshop -> Wineshop[1]))
                .collect(Collectors.toList());

        List<Integer> pickedWineshopPrices = new ArrayList<>();
        for (int i = 0; i < numberOfPickedWineshops; i++) {
            pickedWineshopPrices.add(sortedPriceDifference.get(i)[0]);
        }

        pickedWineshopPrices.sort(Integer::compareTo);

        for (int i = 0; i < pickedWineshopPrices.size(); i++) {
            System.out.print(pickedWineshopPrices.get(i));
            if (i != pickedWineshopPrices.size() - 1) {
                System.out.print(" ");
            }
        }
    }

}
