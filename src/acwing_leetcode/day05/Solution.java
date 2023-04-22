package acwing_leetcode.day05;

class Solution {
    public static void main(String[] args) {
        System.out.println(smallestFactorization(20));
    }
    public static int smallestFactorization(int a) {
        // a找最小b使b所有数位*等于a 凑在一起最小才行
        int l = 1, r = a-1;
        int res = Integer.MAX_VALUE;
        if(a<10){
            return a;
        }
        while(l <= r){
            if(l * r == a){
                res = Math.min(res, getSum(l,r));
                l++;
                r--;
            }else if(l * r < a){
                l++;
            }else{
                r--;
            }
        } 
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    //所有位数相乘。。。。不是 20 可以是 2*10 而是 45 可以4 * 5 20是2*0
    public static int getSum(int l, int r){
        // 8 可以分成 1 8 和 2 4 2 10是210
        //14 15
        int size = 1;
        while(size <= r){
            size *= 10;
        }
        // + *注意溢出问题
        return l * size + r;
    }
}