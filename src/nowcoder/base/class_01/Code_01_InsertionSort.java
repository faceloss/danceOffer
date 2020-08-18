package nowcoder.base.class_01;

import java.util.Arrays;

/**
 * 插入排序设计是：往有序的数组中快速插入一个新的元素
 * 思想：排序数组分两部分，一部分是数组全部元素a，一部分是待插入元素b；将第一部分排序完成，然后插入元素
 * 比如54321，刚开始可以将a看作5，将b看作4321，对待插入元素b中的每一个元素4、3、2、1依次插入a中并且按照指定规则校验（小的往前，或者大的往前）
 *方案：
 * 1、4与5换位，4与5换位，3与4换位置...这种相邻位置对调的插入排序实现，交换操作代价高
 *
 * 2、将新元素取出（挖坑），从左到右依次与已排序的元素比较，如果已排序的元素大于取出的新元素，那么将该元素移动到下一个位置（填坑），
 * 接着再与前面的已排序的元素比较，直到找到已排序的元素小于等于新元素的位置，这时再将新元素插入进去
 *
 * 3、如果方案2中比方案1代价大，可以采用二分查找快速查找b中元素需要在有序数组a中的位置来减少比较（二分查找插入排序变种）
 * */
public class Code_01_InsertionSort {

	/**
	 * 插入排序
	 *
	 * 1. 从第一个元素开始，该元素可以认为已经被排序
	 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
	 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
	 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
	 * 5. 将新元素插入到该位置后
	 * 6. 重复步骤2~5
	 * 这是方案2
	 * @param arr
	 */
	public static void insertionSort2(int[] arr){
		for( int i = 1; i < arr.length; i++ ) {
			int temp = arr[i];    // 取出下一个元素，在已经排序的元素序列中从后向前扫描
			for( int j = i; j >= 0; j-- ) {
				if( j > 0 && arr[j-1] > temp ) {
					arr[j] = arr[j-1];    // 如果该元素（已排序）大于取出的元素temp，将该元素移到下一位置
					System.out.println("Temping:  " + Arrays.toString(arr));
				} else {
					// 将新元素插入到该位置后
					arr[j] = temp;
					System.out.println("Sorting:  " + Arrays.toString(arr));
					break;
				}
			}
		}
	}


	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 4321 ，4不用动，对3往前翻，对2往前翻，对1往前翻
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	// 异或 ，例如 a与b 8 3变换 1000 0011 ，在a会暂时保留所有高位1 然后再与b异或就得到a，
	// 因为b的高位消失剩下a的，然后再与a异或得到b，这个操作可以省去空间
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
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
			insertionSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		insertionSort(arr);
		printArray(arr);
	}

}
