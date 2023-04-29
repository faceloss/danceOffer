package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:20
 **/

import java.util.Scanner;

public class P60 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] inputs = line.split(" ");
            int distanceToA = Integer.parseInt(inputs[0]);
            int distanceToB = Integer.parseInt(inputs[1]);
            int taxiSpeed = Integer.parseInt(inputs[2]);
            int waitingTime = Integer.parseInt(inputs[3]);
            int walkingSpeed = Integer.parseInt(inputs[4]);
            System.out.println(getResult(distanceToA, distanceToB, taxiSpeed, waitingTime, walkingSpeed));
        }
    }

    /**
     * @param distanceToA 到达A医院的距离 (公里)
     * @param distanceToB 到达B医院的距离 (公里)
     * @param taxiSpeed   计程车平均速度  (米/分钟)
     * @param waitingTime 上车等待时间    (分钟)
     * @param walkingSpeed 步行速度        (米/分钟)
     */
    public static String getResult(int distanceToA, int distanceToB, int taxiSpeed, int waitingTime, int walkingSpeed) {
        // 计算坐出租车到A医院所需的时间
        double taxiToA = (distanceToA * 1000.0) / taxiSpeed + waitingTime;
        // 计算步行到B医院所需的时间
        double walkToB = (distanceToB * 1000.0) / walkingSpeed;

        // 判断坐车和步行所需的时间，返回结果
        if (Math.abs(taxiToA - walkToB) < 1e-6) {
            return "Same";
        } else if (taxiToA > walkToB) {
            return "Walk";
        } else {
            return "Taxi";
        }
    }
}


