package nowcoder.base.class_01;

/**荷兰国旗问题，快排序的partition，有一个基准值p和一个数组arr,将比p小的放左边，大的放右边
*
* */
public class Code_08_NetherlandsFlag {

	public static int[] partition(int[] arr, int l, int r, int p) {
		int less = l - 1;//指向最右的小值
		int more = r + 1;//指向最左的大值
		// l是移动坐标
		while (l < more) {
			if (arr[l] < p) {
				swap(arr, ++less, l++);
			} else if (arr[l] > p) {
				swap(arr, --more, l); //1234444444556
			} else {
				l++;
			}
		}
		// 返回的是p值最左和p值最右坐标
		return new int[] { less + 1, more - 1 };
	}

	// for test
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
