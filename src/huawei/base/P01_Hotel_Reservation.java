package huawei.base;

import java.util.*;
import java.util.stream.Collectors;
/**
* 预定酒店：搜到各个价位的酒店n家，心理预期价格是x,找出k个最接近x的酒店并输出
* */
class P01_Hotel_Reservation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfWineshops = scanner.nextInt(); // 给定酒店数
        int numberOfPickedWineshops = scanner.nextInt(); // k个
        int referencePrice = scanner.nextInt(); // 心理预期x

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
        // int[] 直接的排序，按照1位置大小
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
