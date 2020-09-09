package acwing_leetcode.day14;

import acwing_leetcode.NestedInteger;

import java.util.List;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:26
 * @Description:339. 嵌套列表权重和
 * 给定一个嵌套的整数列表，请返回该列表按深度加权后所有整数的总和。
 *
 * 每个元素要么是整数，要么是列表。同时，列表中元素同样也可以是整数或者是另一个列表。
 *
 * 示例 1:
 *
 * 输入: [[1,1],2,[1,1]]
 * 输出: 10
 * 解释: 因为列表中有四个深度为 2 的 1 ，和一个深度为 1 的 2。
 * 示例 2:
 *
 * 输入: [1,[4,[6]]]
 * 输出: 27
 * 解释: 一个深度为 1 的 1，一个深度为 2 的 4，一个深度为 3 的 6。所以，1 + 4*2 + 6*3 = 27。
 */
public class Lc339_NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        NestedInteger x =  new NestedInteger() {
            @Override
            public boolean isInteger() {
                return false;
            }

            @Override
            public Integer getInteger() {
                return null;
            }

            @Override
            public void setInteger(int value) {

            }

            @Override
            public void add(NestedInteger ni) {

            }

            @Override
            public List<NestedInteger> getList() {
                return null;
            }
        };
        return 1;
    }
}
