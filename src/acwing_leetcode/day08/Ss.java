package acwing_leetcode.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ss {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        findAnagrams(s,p);
    }
    public static List<Integer> findAnagrams(String s, String p) {
        // 其实p字符可以让字典++ s窗口可以让它-- 如果刚好最后表中abs==0 那就是异构
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length()<p.length()){
            return res;
        }
        int size = p.length();
        int[] cnt = new int[256];
        for(int i=0; i< size; i++){
            // p标准串去-- 需要s子窗口去++填空
            cnt[s.charAt(i) - 0]++;
            cnt[p.charAt(i) - 0]--;
        }
        int sum = 0;
        for(int tem : cnt){
            sum += Math.abs(tem);
        }
        if(sum == 0){
            res.add(0);
        }
        for(int i = 1; i<=s.length() - size; i++){
            // 新点 和 出窗旧点 比如 abcd 和abc 0是符合的 i=1时候 不管之前是啥 a d都是未知的 要去除
            sum = sum - Math.abs(cnt[s.charAt(i-1)]) - Math.abs(cnt[s.charAt(i+size-1)]);
            cnt[s.charAt(i-1)]--; // 出去的点
            cnt[s.charAt(i+size-1)]++; //进来的点
            sum = sum + Math.abs(cnt[s.charAt(i-1)]) + Math.abs(cnt[s.charAt(i+size-1)]);
            if(sum == 0){
                res.add(i);
            }
        }
        return res;
    }
}