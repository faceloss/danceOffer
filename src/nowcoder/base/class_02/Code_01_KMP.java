package nowcoder.base.class_02;

/**
 * 1 2 1 2 1,即 0 1 2 位置与 2 3 4 位置0开始前缀与j结束后缀
 *对于每模式串 t 的每个元素 t j，都存在一个实数 k ，
 * 使得模式串 t 开头的 k 个字符（t 0 t 1…t k-1）依次与 t j
 * 前面的 k（t j-k t j-k+1…t j-1，
 * 这里第一个字符 t j-k 最多从 t 1 开始，所以 k < j）个字符相同。
 * 如果这样的 k 有多个，则取最大的一个（0～k-1 与 j-k~j-1）,如果k有多个 取最大的
 *  next 数组表示，即 next[ j ]=MAX{ k }
 * */
public class Code_01_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();//匹配串
		char[] ms = m.toCharArray();//模式串
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);//这里面存放着最大相同前后缀，方便回溯
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (mi == 0) {
				//next[0] = -1,第一个位置就不等于。。。。
				si++;
			} else {
				mi = next[mi];
			}
		}
		return mi == ms.length ? si - mi : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		//初始化
		next[0] = -1;
		next[1] = 0; // j位置往前找 0～j-1

		int pos = 2;
		int cn = 0;
		while (pos < next.length) {
			// abbac
			// a b a c a , -1 0 0 1
			if (ms[pos - 1] == ms[cn]) {
				next[pos++] = ++cn;
			} else if (cn > 0) {
				//why?(因为从cn位开始就不相同了，next表示可利用信息)
				cn = next[cn];
			} else {
				// abaabc -1 0 0 1 1 2
				next[pos++] = 0;
			}
		}
		return next;
	}
	// a b a a d   -1 0 0 1

	public static void main(String[] args) {
		String str = "abcabcaasdadbabaccc";
		String match = "baba";
		// 0 1 2 3 4 5 状态 5状态不需要，一共需要len个状态表示前index个 0状态-1 1状态0 2状态0 3状态1 4状态2
		int[] res = getNextArray(match.toCharArray());
		for (Integer in: res){
			System.out.println(in);
		}
		System.out.println(getIndexOf(str, match));

	}

}
