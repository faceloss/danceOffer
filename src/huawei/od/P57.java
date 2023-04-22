package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.*;

public class P57 {
    // 将输入字符串按照空格分隔成整数数组
    public static List<Integer> splitString(String input_str) {
        List<Integer> params = new ArrayList<>();
        while (input_str.indexOf(" ") != -1) {
            int found = input_str.indexOf(" ");
            params.add(Integer.parseInt(input_str.substring(0, found)));
            input_str = input_str.substring(found + 1);
        }
        params.add(Integer.parseInt(input_str));
        return params;
    }

    public static void main(String[] args) {
        // 获取输入
        Scanner scanner = new Scanner(System.in);
        String input_str1 = scanner.nextLine();
        int rows = Integer.parseInt(input_str1);

        String input_str2 = scanner.nextLine();
        int cols = Integer.parseInt(input_str2);

        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            String input_str = scanner.nextLine();
            List<Integer> row = splitString(input_str);
            matrix.add(row);
        }

        // 统计每个数字在矩阵中出现的位置
        Map<Integer, List<List<Integer>>> num_positions_map = new HashMap<>();
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                // 将坐标转化为数组表示
                List<Integer> position = new ArrayList<>();
                position.add(i);
                position.add(j);

                if(num_positions_map.containsKey(matrix.get(i).get(j))){
                    num_positions_map.get(matrix.get(i).get(j)).add(position);
                }else {
                    List<List<Integer>> temp_list = new ArrayList<>();
                    num_positions_map.put(matrix.get(i).get(j), temp_list);
                    num_positions_map.get(matrix.get(i).get(j)).add(position);
                }
            }
        }

        // 计算每个元素到相同数字的最小距离
        List<List<Integer>> result_list = new ArrayList<>();
        for(int i=0; i<rows; i++){
            List<Integer> temp_list = new ArrayList<>();
            for(int j=0; j<cols; j++){
                int num = matrix.get(i).get(j);
                List<List<Integer>> positions_list = num_positions_map.get(num);

                // 只有一个相同数字
                if(positions_list.size() == 1){
                    temp_list.add(-1);
                    continue;
                }

                // 找到最小距离
                int min_distance = Integer.MAX_VALUE;
                for(int k=0; k<positions_list.size(); k++){
                    List<Integer> position = positions_list.get(k);
                    int distance = Math.abs(position.get(0)-i) + Math.abs(position.get(1)-j);

                    // 距离为0则跳过
                    if(distance == 0){
                        continue;
                    }

                    min_distance = Math.min(min_distance, distance);
                }

                temp_list.add(min_distance);
            }
            result_list.add(temp_list);
        }

        // 输出结果
        System.out.print("[");
        for (int i=0;i<result_list.size();i++) {
            System.out.print("[");
            for (int j=0;j<result_list.get(0).size();j++) {
                System.out.print(result_list.get(i).get(j));
                if (j!=result_list.get(0).size()-1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i!=result_list.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

        scanner.close();
    }
}


