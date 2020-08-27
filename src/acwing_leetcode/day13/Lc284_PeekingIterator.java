package acwing_leetcode.day13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: danceOffer 顶端迭代器
 * @description: 给定一个迭代器类的接口，接口包含两个方法： next() 和 hasNext()。
 * 设计并实现一个支持 peek() 操作的顶端迭代器 -- 其本质就是把原本应由 next() 方法返回的元素 peek() 出来。
 * 示例:  假设迭代器被初始化为列表 [1,2,3]。  调用 next() 返回 1，得到列表中的第一个元素。 现在调用 peek() 返回 2，下一个元素。
 * 在此之后调用 next() 仍然返回 2。 最后一次调用 next() 返回 3，末尾元素。在此之后调用 hasNext() 应该返回 false。
 * 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/peeking-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-27 09:53
 **/

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        Iterator it = nums.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        PeekingIterator p = new PeekingIterator(nums.iterator());
        System.out.println(p.peek());
        System.out.println(p.next());
    }
    private Integer index;
    private Iterator<Integer> nums;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.nums = iterator;
        index = null;//维护的头部值 如果null就是没初始化返回
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(index != null){
            return index;
        }
        index = nums.next();
        return index;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        // 获取next很简单 如果上来就next 不需要维护peek，index有值了 就需要维护了（next后 peek要变化了 所以置null）
        if (index != null) {
            int res = index;
            index = null;
            return res;
        }
        return nums.next();
    }

    @Override
    public boolean hasNext() {
        return index != null || nums.hasNext();
    }
}
