//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 2007 👎 0

package leetcode.leetcode.editor.cn;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//java:有效的括号
public class P20_ValidParentheses{
    public static void main(String[] args){
        Solution solution = new P20_ValidParentheses().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        int len = s.length();
        if(len % 2 == 1){
            return false;
        }
        Map<Character,Character> chars1 = new HashMap<Character, Character>(){{
            put(')','(');
            put('}','{');
            put(']','[');

        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < len ; i++) {
            // 假如是左就压栈、右会判断是否在表中，并且栈中弹出要对上
            Character temp = s.charAt(i);
            //非右就压栈
            if(chars1.containsKey(temp)){
                //找不到对齐
                if(stack.isEmpty() || stack.peek() != chars1.get(temp))
                    return false;
                //能找到弹出对齐的 继续
                stack.pop();
            }else{
                stack.push(temp);
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}