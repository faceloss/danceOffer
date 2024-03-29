package acwing.day02;

/**
 * @program: danceOffer
 * week2需要：第 11 ~ 30 题
 *
 * @description:11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * @author: mobing_yin
 * @create: 2020-09-07 16:24
 **/

public class Lc11_ContainerWithMostWater {
    // 这题会让人以为里面的坐标会占据水的体积。。。
    public int maxArea(int[] height) {
        int start=0;
        int end=height.length-1;
        int res=Integer.MIN_VALUE;
        while(start<end){
            int temp=Math.min(height[start],height[end]);
            int gap=end-start;
            if(temp*gap>res){
                res=temp*gap;
            }
            // gap都是减小1 哪个高度低哪个移动
            if(height[start]<height[end]){
                start++;
            }else{
                end--;
            }
        }
        return res;
    }
}
