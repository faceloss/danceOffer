package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/


import java.util.*;

class P42 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sampleCount = scanner.nextInt();
        int volunteerCount = scanner.nextInt();

        Integer[] efficiencies = new Integer[sampleCount];
        for (int i = 0; i < sampleCount; i++) {
            efficiencies[i] = scanner.nextInt();
        }

        // 按效率从高到低排序
        Arrays.sort(efficiencies, Collections.reverseOrder());

        int result = 0; // 总效率值
        int volunteerIndex = 0; // 当前志愿者的下标
        int sampleIndex = 0; // 当前采样员的下标

        // 分支1: 志愿者<采样员，优先将志愿者分配给效率高的采样员
        if (volunteerCount < sampleCount) {
            for (int i = 0; i < sampleCount; i++) {
                if (volunteerIndex < volunteerCount) {
                    result += efficiencies[i];
                    volunteerIndex++;
                } else {
                    result += (int)(efficiencies[i] * 0.8);
                }
            }
            // 分支2: 志愿者>=采样员，先给每个采样员都分配一个志愿者
        } else {
            int maxVolunteerCount = Math.min(volunteerCount, 4 * sampleCount); // 最大有效志愿者数量
            for (int i = 0; i < sampleCount; i++) {
                result += efficiencies[i];
                volunteerIndex++;
            }

            while (volunteerIndex < maxVolunteerCount) {
                result += (int)(efficiencies[sampleIndex] * 0.1);
                volunteerIndex++;
                if (++sampleIndex == sampleCount) {
                    sampleIndex = 0;
                }
            }
        }

        // 剥夺低效率采样员的志愿者给高效率的采样员
        while (sampleIndex < sampleCount - 1) {
            if (efficiencies[sampleIndex] * 0.1 > efficiencies[sampleCount - 1] * 0.2) {
                result += (int)(efficiencies[sampleIndex] * 0.1 - efficiencies[sampleCount - 1] * 0.2);
                sampleIndex++;
                volunteerIndex--;
            } else {
                break;
            }
        }

        System.out.println(result);
    }
}

