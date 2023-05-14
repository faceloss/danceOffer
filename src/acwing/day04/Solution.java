package acwing.day04;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.movingCount(16,8,4));
    }
    //可以找到 40 和 04
    //1 2 3 4 k+1, k+1 *k+2
    public int movingCount(int m, int n, int k) {
        // 1 2 3 4 5 =15个
        //硬做 遍历每一个点看是否可达
        int res = 0;
        for(int i =0;i<m;i++){
            for(int j=0;j<n;j++){
                if(match(i,j,k)){
                    res++;
                }
            }
        }
        return res;
    }
    // 16 8 4 , 0 1 2 3 4 5
    public boolean match(int m, int n, int k){
        int cnt = 0;
        while(m!=0 || n!=0){
            cnt += m%10;
            cnt += n%10;
            m = m/10; 
            n = n/10; 
        }
        return cnt <= k;
    }
}