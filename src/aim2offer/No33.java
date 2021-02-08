package aim2offer;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出所有数字中最小的一个。
 * 例如输入数组{3,32,321}，则打印出这3个数字能排成的最小数字321323
 */

public class No33 {

    public static void main(String[] args) {
        int[] array = {321, 32, 3};
        printMin(array);
    }

    private static void printMin(int[] array) {
        int[] clone = array.clone();
        printMin(clone, 0, clone.length - 1);
        for (int i : clone)
            System.out.print(i);
    }

    private static void printMin(int[] array, int start, int end) {

        if (start < end) {
            int main_number = array[end];
            int small_cur = start;
            for (int j = start; j < end; j++) {
                if (isSmall(String.valueOf(array[j]), String.valueOf(main_number))) {
                    int temp = array[j];
                    array[j] = array[small_cur];
                    array[small_cur] = temp;
                    small_cur++;
                }
            }
            array[end] = array[small_cur];
            array[small_cur] = main_number;
            printMin(array, 0, small_cur - 1);
            printMin(array, small_cur + 1, end);
        }

    }

    public static boolean isSmall(String m, String n) {
        String left = m + n;
        String right = n + m;
        boolean result = false;
        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) < right.charAt(i))
                return true;
            else if (left.charAt(i) > right.charAt(i))
                return false;
        }

        return result;
    }


    class Solution {
        public String minNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for(int i = 0; i < nums.length; i++)
                strs[i] = String.valueOf(nums[i]);
            fastSort(strs, 0, strs.length - 1);
            StringBuilder res = new StringBuilder();
            for(String s : strs)
                res.append(s);
            return res.toString();
        }
        void fastSort(String[] strs, int l, int r) {
            if(l >= r) return;
            int i = l, j = r;
            // 从j位置找比l小的找到停 从i位置找比l大的找到停 i j 两个位置 一大 一小 交换，因为一直是和l位置比较 比较完交换 i l ,将patition值放到i位置 继续递归l i-1 ,i+1 r
            String tmp = strs[i];
            while(i < j) {
                while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
                while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
                tmp = strs[i];
                strs[i] = strs[j];
                strs[j] = tmp;
            }
            strs[i] = strs[l];
            strs[l] = tmp;
            fastSort(strs, l, i - 1);
            fastSort(strs, i + 1, r);
        }
    }
}
