package aim2offer;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 */

public class No29 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        int[] arr1 = {1,3,5};
        int[] res = findNum2(arr1);
        System.out.println(findNum2(arr1));
    }
    private static int[] findNum2(int[] arr) {
        if (arr == null)
            return null;
        int len = arr.length;
        int n = len + 2;
        int[] cnt = new int[n+1];
        // 1到n
        for (int i = 0; i < arr.length; i++) {
            cnt[arr[i]]++;
        }
        int index = 0;
        int[] res = new int[2];
        for (int i = 1; i < cnt.length; i++) {
            if (cnt[i] == 0){
                res[index++] = i;
            }
        }
        return res;
    }

    private static Integer findNum(int[] arr) {
        if (arr == null)
            return null;

        int result = arr[0];
        int count = 1;
        // 统计某个字符的数量， 数量多的字符总会把其他字符count--掉（因为总和超过一半）
        for (int i = 1; i < arr.length; i++) {
            if (count == 0) {
                result = arr[i];
                count = 1;
            } else if (arr[i] == result) {
                count++;
            } else {
                count--;
            }
        }
        return result;
    }
}
