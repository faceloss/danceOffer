package codeforce.advance;

import java.util.*;
import java.io.*;

public class P14{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int nodeNum = Integer.parseInt(inputs[0]);
        int edgeNum = Integer.parseInt(inputs[1]);
        List<int[]> edges = new ArrayList<>();
        for(int i=0; i<edgeNum; i++){
            String[] edgeInput = br.readLine().split(" ");
            int[] edge = new int[]{Integer.parseInt(edgeInput[0]), Integer.parseInt(edgeInput[1])};
            edges.add(edge);
        }
        int count = 0;
        for(int i=0; i<(1<<nodeNum); i++){
            boolean flag = true;
            Map<Integer, Integer> colors = new HashMap<>();
            for(int[] edge : edges){
                if(((i>>(nodeNum-edge[0]))&1)==1 && ((i>>(nodeNum-edge[1]))&1)==1){
                    flag = false;
                    break;
                }else if(((i>>(nodeNum-edge[0]))&1)==1){
                    colors.put(edge[0], 1);
                }else if(((i>>(nodeNum-edge[1]))&1)==1){
                    colors.put(edge[1], 1);
                }
            }
            if(flag){
                count++;
            }
        }
        System.out.println(count);
    }
}
