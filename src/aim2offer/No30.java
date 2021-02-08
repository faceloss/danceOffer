/**
 * 题目：输入n个整数，输出其中最小的k个。
 * 例如输入1，2，3，4，5，6，7和8这8个数字，则最小的4个数字为1，2，3和4。
 */
package aim2offer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class No30 {

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 2, 7, 8, 9, 10, 14, 16};
        System.out.println(minK(arr, 1));
        System.out.println(minK(arr, 2));
        System.out.println(minK(arr, 3));
        System.out.println(minK(arr, 4));
        System.out.println(minK(arr, 5));
        System.out.println(minK(arr, 6));
        Integer[] res =  minK1(arr, 3);
        System.out.println(1);
        // on时间 ok空间
        int[] arr3 = {0,0,0,2,0,5};
        int kk = 0;
        System.out.println(getLeastNumbers(arr3,0));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if(arr == null || arr.length < k || k <=0 ){
            return null;
        }
        Queue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            if(i < k){
                queue.add(arr[i]);
            }else if(arr[i] < queue.peek()){
                queue.poll();
                queue.add(arr[i]);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    public static Integer[] minK1(int[] arr, int k) {
        if(arr == null || arr.length <k){
            return null;
        }
        Queue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }
        for (int i = k; i <arr.length ; i++) {
            if(arr[i] < queue.peek()){
                queue.poll();
                queue.add(arr[i]);
            }
        }
        Integer[] res = new Integer[k];
        return queue.toArray((Integer[])res);
    }

    public static int minK(int[] arr, int k) {
        return minK(arr, k, 0, arr.length - 1);
    }

    public static int minK(int[] arr, int k, int start, int end) {
        int mid = partition(arr, start, end);
        if (mid - start == k - 1) {
            return arr[mid];
        } else if (mid - start > k - 1) {
            return minK(arr, k, start, mid - 1);
        } else {
            return minK(arr, k - 1 - (mid - start), mid + 1, end);
        }
    }


    public static int partition(int[] arr, int start, int end) {
        int key = arr[start];
        int keyIndex = start;
        start++;
        for (int i = start; i <= end; i++) {
            if (arr[i] < key) {
                swap(arr, i, start);
                start++;
            }
        }
        swap(arr, keyIndex, start - 1);
        return start - 1;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
