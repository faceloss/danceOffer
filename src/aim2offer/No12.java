package aim2offer;

/**
 * 输入数字n，按顺序打印出从1最大的n位十进制数。
 * 比如输入3，则打印出1、2、3一直到最大的3位数即999
 */

public class No12 {

    public static void main(String[] args) {
        printNum(3);
    }

    private static void printNum(int n) {
        if (n < 0)
            return;
        int[] array = new int[n];
        printArray(array, 0);
    }

    //带着array空间、n索引进去，然后应该填入的值是i 0～9 到何时打印，收集完了打印
    private static void printArray(int[] array, int n) {

        if (n != array.length) {
            for (int i = 0; i < 10; i++) {
                array[n] = i;
                printArray(array, n + 1);
            }
        } else {
            boolean flag = false;
            for (int j = 0; j < array.length; j++) {
                if (array[j] != 0) {
                    flag = true;
                }
                if (flag) {
                    System.out.print(array[j]);
                }
            }
            // 去掉空白行
            if (flag) {
                System.out.println();
            }
        }
    }
}
