package codeforce.advance;

import java.util.*; //导入java.util包

public class P40 { //定义类Main
    public static List<Integer> split(String input_str) { //定义函数split，返回一个List<Integer>类型的容器，参数为字符串类型的input_str
        List<Integer> heights = new ArrayList<>(); //定义一个List<Integer>类型的容器heights
        while (input_str.indexOf(" ") != -1) { //当input_str中包含空格时
            int found = input_str.indexOf(" "); //找到空格的位置
            heights.add(Integer.parseInt(input_str.substring(0, found))); //将空格前面的字符串转化为int类型并加入heights容器中
            input_str = input_str.substring(found + 1); //将input_str更新为去掉空格及其前面的字符串
        }    
        heights.add(Integer.parseInt(input_str)); //将最后一个字符串转化为int类型并加入heights容器中
        return heights; //返回heights容器
    }

    public static void main(String[] args) { //主函数
        Scanner sc = new Scanner(System.in); //创建Scanner对象
        String input_str = sc.nextLine(); //从标准输入流中读取一行字符串并赋值给input_str
        List<Integer> heights = split(input_str); //将input_str传递给split函数并将返回的容器赋值给heights

        int left = 0; //定义变量left，初始值为0
        int right = heights.size() - 1; //定义变量right，初始值为heights容器的大小减1

        List<Integer> result = new ArrayList<>(Arrays.asList(0, 0, 0)); //定义一个List<Integer>类型的容器result，初始值为{0,0,0}
        int capacity = 0; //定义变量capacity，初始值为0

        while (left < right) { //当left小于right时，进行循环
            int sum = 0; //定义变量sum，初始值为0
            int lower = Math.min(heights.get(left), heights.get(right)); //定义变量lower，为left和right位置上的数中的较小值

            for (int i = left; i <= right; i++) { //从left遍历到right
                sum += Math.max(0, lower - heights.get(i)); //将lower减去当前位置上的数，如果结果大于0则加上该结果
            }

            if (sum >= capacity) { //如果sum大于等于capacity
                result = new ArrayList<>(Arrays.asList(left, right, sum)); //将{left, right, sum}赋值给result
                capacity = sum; //将sum赋值给capacity
            }

            if (heights.get(right-1) >= heights.get(left) && heights.get(right-1) >= heights.get(right)) right--; //如果right-1位置上的数大于等于left位置上的数且大于等于right位置上的数，则将right减1
            else if (heights.get(left) < heights.get(right)) left++; //如果left位置上的数小于right位置上的数，则将left加1
            else if (heights.get(left + 1) >= heights.get(right) && heights.get(left+1) >= heights.get(left)) left++; //如果left+1位置上的数大于等于right位置上的数且大于等于left位置上的数，则将left加1
            else if (heights.get(left) > heights.get(right)) right--; //如果left位置上的数大于right位置上的数，则将right减1
            else right--; //否则将right减1
        }

        if (result.get(2) == 0) { //如果result中的第三个数为0
            System.out.println(0); //输出0
        } else{
            System.out.println(result.get(0) + " " + result.get(1) + ":" + result.get(2)); //否则输出result中的三个数
        }
    }
}
