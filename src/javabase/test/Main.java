package javabase.test;

public class Main {
    public static void main(String[] args) {
        int arr[] = {1,-3,4,10};
//        while(in.hasNextInt()){
//            arr[i++] = in.nextInt();
//            if(i == n){
//                break;
//            }
//        }

        int[] res = get2Index(arr);
        System.out.println(res[0]+ "|" + res[1]);
    }
    public static int[] get2Index(int[] arr){
        int[] sum = new int[arr.length+1];
        for(int i = 1; i<= arr.length; i++){
            sum[i] = sum[i-1] + arr[i-1];
        }
        int max = Integer.MIN_VALUE;
        int minI = 0;
        int maxI = 0;
        for (int i = 0; i<arr.length; i++){
            for (int j = i; j<arr.length; j++) {
                // sum 表示前i个元素 因此 你要算i到j 也就是sum[j+1] - sum[i]
                int val = sum[j+1] - sum[i];
                if(val > max){
                    max = val;
                    minI = i;
                    maxI = j;
                }
            }
        }
        return new int[]{minI, maxI};
    }

    public static int[] getRes(int[] arr){
        int minv = arr[0];
        int maxv = arr[0];
        int min = 0, max = 0;
        for(int i = 1; i< arr.length ; i++){
            arr[i] += Math.max(arr[i-1] , 0);// 到i位置可以得到的最大值（前面非0 加上当前）
            if(minv > arr[i] && arr[i] < 0){
                minv = arr[i];
                min = i;
            }
            if(maxv < arr[i]){
                maxv = arr[i];
                max = i;
            }

        }
        return new int[]{min, max};
    }
}