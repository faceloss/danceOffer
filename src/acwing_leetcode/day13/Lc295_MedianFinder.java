package acwing_leetcode.day13;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @program: danceOffer
 * @description:
 * 295. 数据流的中位数 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，  [2,3,4] 的中位数是 3  [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：  void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。 示例：  addNum(1) addNum(2) findMedian() -> 1.5 addNum(3)  findMedian() -> 2
 * 进阶:  如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * @author: mobing_yin
 * @create: 2020-08-27 09:57
 **/

public class Lc295_MedianFinder {

    /** initialize your data structure here. */
    public Lc295_MedianFinder() {

    }
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });

    public void addNum(int num) {
        if (this.maxHeap.isEmpty()) {
            this.maxHeap.add(num);
            return;
        }
        if (this.maxHeap.peek() >= num) {
            this.maxHeap.add(num);
        } else {
            if (this.minHeap.isEmpty()) {
                this.minHeap.add(num);
                return;
            }
            if (this.minHeap.peek() > num) {
                this.maxHeap.add(num);
            } else {
                this.minHeap.add(num);
            }
        }
        this.modifyTwoHeapsSize();
    }

    public void addNumber(Integer num) {
        if (this.maxHeap.isEmpty()) {
            this.maxHeap.add(num);
            return;
        }
        if (this.maxHeap.peek() >= num) {
            this.maxHeap.add(num);
        } else {
            if (this.minHeap.isEmpty()) {
                this.minHeap.add(num);
                return;
            }
            if (this.minHeap.peek() > num) {
                this.maxHeap.add(num);
            } else {
                this.minHeap.add(num);
            }
        }
        this.modifyTwoHeapsSize();
    }

    public double findMedian() {
        long maxHeapSize = this.maxHeap.peek();
        long minHeapSize = this.minHeap.peek();
        if (maxHeapSize + minHeapSize == 0) {
            return 0.0 ;
        }
        Integer maxHeapHead = this.maxHeap.peek();
        Integer minHeapHead = this.minHeap.peek();
        if (((maxHeapSize + minHeapSize) & 1) == 0) {
            return (maxHeapHead + minHeapHead) / 2;
        } else if (maxHeapSize > minHeapSize) {
            return maxHeapHead;
        } else {
            return minHeapHead;
        }
    }

    private void modifyTwoHeapsSize() {
        if (this.maxHeap.size() == this.minHeap.size() + 2) {
            this.minHeap.add(this.maxHeap.poll());
        }
        if (this.minHeap.size() == this.maxHeap.size() + 2) {
            this.maxHeap.add(this.minHeap.poll());
        }
    }


    public static void main(String[] args) {
        Lc295_MedianFinder media = new Lc295_MedianFinder();
        media.addNum(1);
        media.addNum(2);
        media.addNum(3);
        media.addNum(4);
        media.addNum(5);
        media.addNum(6);



        System.out.println(media.findMedian());


    }

}
