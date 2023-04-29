package huawei.advance;

import java.util.*; //导入java.util包

public class P02 { //定义类Main
    public static List<Integer> split(String str) { //定义函数split，将字符串转换为整数List
        List<Integer> nums = new ArrayList<>(); //初始化整数List
        while (str.indexOf(" ") != -1) { //当字符串中还有空格时
            int found = str.indexOf(" "); //找到空格的位置
            nums.add(Integer.parseInt(str.substring(0, found))); //将空格前的字符串转换为整数并加入List
            str = str.substring(found + 1); //将字符串更新为去除空格的后半部分
        }    
        nums.add(Integer.parseInt(str)); //将最后一部分字符串转换为整数并加入List
        return nums; //返回整数List
    }

    public static void main(String[] args) { //主函数
        Scanner scanner = new Scanner(System.in); //创建Scanner对象
        int n = Integer.parseInt(scanner.nextLine()); //读取一行字符串并将其转换为整数
 
        String op_str = scanner.nextLine(); //读取一行字符串
        List<Integer> nums = split(op_str); //将字符串转换为整数List
 
        List<Integer> bit_info = new ArrayList<>(Collections.nCopies(100, 0)); //定义长度为100的整数List，初始化为0
        
        for (int num : nums) { //遍历整数List
            String num_binary_str = Integer.toBinaryString(num); //将整数转换为二进制字符串
            num_binary_str = num_binary_str.replaceFirst("^0+(?!$)", ""); //去除字符串前面的0
            int len = num_binary_str.length(); //计算字符串长度
        
            if ("".equals(num_binary_str)) { //如果字符串为空
                bit_info.set(0, bit_info.get(0) + 1); //将bit_info[0]加1
            } else { //否则
                bit_info.set(len, bit_info.get(len) + 1); //将bit_info[字符串长度]加1
            }
        }
        
        int res = 0; //定义整数res，初始化为0
        for (int i = 0; i < bit_info.size(); i++) { //遍历bit_info
            for (int j = i + 1; j < bit_info.size(); j++) { //遍历bit_info
                res += bit_info.get(i) * bit_info.get(j); //计算res的值
            }
        }
        System.out.println(res); //输出res的值
    }
}
