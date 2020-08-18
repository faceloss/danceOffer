package nowcoder.base.class_01;

import java.util.Arrays;

/**
 * @program: danceOffer
 * @description: 希尔排序
 * 希尔排序也称递减增量排序，是插入排序的一种高速且稳定的改进版本
 * 将整个待排序序列分割成若干个子序列分别进行插入排序，待整个序列中记录整体有序，再对全体记录进行插入排序
 * 即：将待排序数组按照步长gap进行分组，然后将每组的元素利用直接插入排序的方法进行排序；每次再将gap折半减小，循环上述操作；
 * 当gap=1时，利用直接插入，完成排序。
 *
 * @author: mobing_yin
 * @create: 2020-08-18 14:52
 **/

public class Code_13_ShellSort {

    /**
     * 希尔排序
     *
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，直到增量为1）
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * @param arr  待排序数组
     */
    public static void shellSort(int[] arr){
        int gap = arr.length / 2;
        for (; gap > 0; gap /= 2) {      //不断缩小gap，直到1为止
            for (int j = 0; (j+gap) < arr.length; j++){     //使用当前gap进行组内插入排序
                for(int k = 0; (k+gap)< arr.length; k += gap){
                    if(arr[k] > arr[k+gap]) {
                        int temp = arr[k+gap];      //交换操作
                        arr[k+gap] = arr[k];
                        arr[k] = temp;
                        System.out.println("    Sorting:  " + Arrays.toString(arr));
                    }
                }
            }
        }
    }

    /**
     * 上面的代码 我觉得有问题来自：https://itimetraveler.github.io/2017/07/18/%E5%85%AB%E5%A4%A7%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E6%80%BB%E7%BB%93%E4%B8%8Ejava%E5%AE%9E%E7%8E%B0/
     * 自己按照思路实现
     * */

    public static void shell(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        int gap = arr.length/2;
        for (; gap>0; gap/=2){
            // 按照gap分组做插入排序
            for(int i=0; (i+gap)<arr.length; i++){
                // 分成了n-gap组
                for (int j = 0; (j+gap)<arr.length; j+=gap) {
                    if(arr[j]>arr[j+gap]){
                        swap(arr, j, j+gap);
                    }
                }
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j]; // 保留高位1
        arr[j] = arr[i] ^ arr[j]; //获取i的高位 即arr[i]
        arr[i] = arr[i] ^ arr[j]; //当前arr[j]就是初始的arr[i]，刚好取到j的高位
    }
    // 例如 7654321这7个值，步长3开始 将741做插入63做插入52，41，
    public static void main(String[] args) {
        int[] arr = {49,38,65,97,76,13,27,49,55,04};
        shellSort(arr);
        System.out.println("    Sorting...:  " + Arrays.toString(arr));
        int[] arr1 = {49,38,65,97,76,13,27,49,55,04};
        shell(arr1);
        System.out.println("    Sorting...:  " + Arrays.toString(arr));
    }
}
