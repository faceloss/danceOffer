package acwing.day01;

/**
 * @program: danceOffer
 * @description: 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，
 * 排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例 1:  输入: s = "LEETCODEISHIRING", numRows = 3 输出: "LCIR ETOESIIG EDHN"
 * 示例 2:  输入: s = "LEETCODEISHIRING", numRows = 4 输出: "LDRE OEIIECIH NTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/zigzag-conversion 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-26 16:38
 **/

public class Lc6_ZigZag_Conversion {
    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING",3));
    }
    //从图可以看出排列成啥样与字符串长度、输入行数、排列规则z有关,一次完整的结尾是从0～2*row-3坐标
    // 输入row i从0～row-1 坐标：i + (2*row-2) * j 第n行
    public static String convert(String s, int numRows) {
        if(s==null || s.length()==0){
            return "";
        }
        if(numRows==1){
            return s;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int index = 0;
        char[] newChars = new char[n];
        // i代表第几行 0～numRows
        for (int i = 0; i < numRows; i++) {
            // time表示有几次完整循环
            int time = (n-1)/(2*(numRows-1));
            //j表示第j个循环中
            /**
             * L   C   I   R
             * E T O E S I I G
             * E   D   H   N
             *
             *
             *  * L     D     R
             *  * E   O E   I I
             *  * E C   I H   N
             *  * T     S     G
            * */
            for (int j = 0; j <=time; j++) {
                if(i==0 || i==numRows-1){
                    if(i + j*(2*numRows-2) < n){
                        newChars[index++] = chars[i + j*2*(numRows-1)];
                    }
                }else{
                    if(i + j*(2*numRows-2) < n){
                        newChars[index++] = chars[i + j*2*(numRows-1)];
                    }
                    if((2*numRows-2-i) + j*(2*numRows-2) < n){
                        newChars[index++] = chars[(2*numRows-2-i) + j*2*(numRows-1)];
                    }
                }
            }
        }
        return new String(newChars);
    }
}
