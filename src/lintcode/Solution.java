package lintcode;

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort (int[] arr) {
        // write code here
        quickSort(arr, 0, arr.length-1);
        return arr;
    }
    public void quickSort(int[] arr, int start, int end){
        // 终止条件就是只有一个。。。 还是台
        if(start >= end) return;
        int partition = arr[start];
        int l = start;
        int r = end;
        while(l < r){
            while(l<r && arr[l] <= partition){
                l++;
            }
            while(r>l && arr[r] >= partition){
                r--;
            }
            swap(arr, l, r);
            l++;
            r--;
        }
        // r l
        quickSort(arr, start, l-1);
        quickSort(arr, l, end);
    }
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}