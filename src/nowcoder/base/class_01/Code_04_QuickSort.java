package nowcoder.base.class_01;

import java.util.Arrays;
import java.util.Stack;

/**
* 快排：随机、简单递归、非递归
 *
 * 掌握快排的递归、非递归实现即可
* */
public class Code_04_QuickSort {

	/**
	 * 快速排序（递归）
	 *
	 * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
	 * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
	 * ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
	 * @param arr   待排序数组
	 * @param low   左边界
	 * @param high  右边界
	 */
	public static void quickSort1(int[] arr, int low, int high){
		if(arr.length <= 0) return;
		if(low >= high) return;
		int left = low;
		int right = high;
		// 保存left位置值temp，将right往左移动找到小于temp的值并放在left上，将left往右移动找到大于temp的值并且放在right上
		// 4 8 6 5 2 3 7
		// 以4为基准 从右往左找到一个小值交换、从左往右找找到一个大值交换，因为left位置的值已经存在temp，right位置的值已经存在left，所以不存在丢失值
		int temp = arr[left];   //挖坑1：保存基准的值
		while (left < right){
			while(left < right && arr[right] >= temp){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
				right--;
			}
			arr[left] = arr[right];
			while(left < right && arr[left] <= temp){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
				left++;
			}
			arr[right] = arr[left];
		}
		arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
		System.out.println("Sorting: " + Arrays.toString(arr));
		quickSort(arr, low, left-1);
		quickSort(arr, left+1, high);
	}

	/**
	 * 快速排序（非递归）
	 *
	 * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
	 * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
	 * ③. 把分区之后两个区间的边界（low和high）压入栈保存，并循环①、②步骤
	 * @param arr   待排序数组
	 */
	public static void quickSortByStack(int[] arr){
		if(arr.length <= 0) return;
		Stack<Integer> stack = new Stack<Integer>();

		//初始状态的左右指针入栈
		stack.push(0);
		stack.push(arr.length - 1);
		while(!stack.isEmpty()){
			int high = stack.pop();     //出栈进行划分
			int low = stack.pop();

			int pivotIdx = partition1(arr, low, high);

			//保存中间变量
			if(pivotIdx > low) {
				stack.push(low);
				stack.push(pivotIdx - 1);
			}
			if(pivotIdx < high && pivotIdx >= 0){
				stack.push(pivotIdx + 1);
				stack.push(high);
			}
		}
	}

	private static int partition1(int[] arr, int low, int high){
		if(arr.length <= 0) return -1;
		if(low >= high) return -1;
		int l = low;
		int r = high;

		int pivot = arr[l];    //挖坑1：保存基准的值
		while(l < r){
			while(l < r && arr[r] >= pivot){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
				r--;
			}
			arr[l] = arr[r];
			while(l < r && arr[l] <= pivot){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
				l++;
			}
			arr[r] = arr[l];
		}
		arr[l] = pivot;   //基准值填补到坑3中，准备分治递归快排
		return l;
	}

	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int l, int r) {
		if (l < r) {
			swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
			// 随机快排序，将arr[r]的值作为partition的准星，整体有序，再分治。。。递归下去
			int[] p = partition(arr, l, r);

			quickSort(arr, l, p[0] - 1);
			quickSort(arr, p[1] + 1, r);
		}
	}

	public static int[] partition(int[] arr, int l, int r) {
		int less = l - 1;
		int more = r;
		// 用less和more做移位 r里面存放基准值 l是移动的坐标
		while (l < more) {
			if (arr[l] < arr[r]) {
				// 小于的less和l一起++
				swap(arr, ++less, l++);
			} else if (arr[l] > arr[r]) {
				//大于的与后面的more-1交换 more- 但是l未知不变
				swap(arr, --more, l);
			} else {
				//等于的l坐标++
				l++;
			}
		}
		swap(arr, more, r);
		return new int[] { less + 1, more };
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			quickSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		quickSort(arr);
		printArray(arr);

	}

}
