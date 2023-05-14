package codeforce.advance;

import java.util.*;

public class P45 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> guessInfos = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String guessNum = scanner.next();
            String guessResult = scanner.next();
            guessInfos.add(Arrays.asList(guessNum, guessResult));
        }

        List<Set<String>> possibleNums = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Set<String> set = new HashSet<>();
            set.addAll(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
            possibleNums.add(set);
        }

        for (List<String> guessInfo : guessInfos) {
            String guessNum = guessInfo.get(0);
            String guessResult = guessInfo.get(1);
            int countA = guessResult.charAt(0) - '0';
            int countB = guessResult.charAt(2) - '0';
            if (countA == 0) {
                for (int i = 0; i < 4; i++) {
                    String c = guessNum.substring(i, i+1);
                    if (countB == 0) {
                        possibleNums.get(0).remove(c);
                        possibleNums.get(1).remove(c);
                        possibleNums.get(2).remove(c);
                        possibleNums.get(3).remove(c);
                    }
                    else {
                        possibleNums.get(i).remove(c);
                    }
                }
            }
        }

        List<String> cache = new ArrayList<>();
        for (String c1 : possibleNums.get(0)) {
            for (String c2 : possibleNums.get(1)) {
                for (String c3 : possibleNums.get(2)) {
                    for (String c4 : possibleNums.get(3)) {
                        String answer = c1 + c2 + c3 + c4;
                        int count = 0;
                        for (List<String> guessInfo : guessInfos) {
                            String guess = guessInfo.get(0);
                            String expectResult = guessInfo.get(1);
                            int countA = 0;
                            int countB = 0;
                            int[] answerArr = new int[10];
                            int[] guessArr = new int[10];
                            for (int i = 0; i < guess.length(); i++) {
                                int c1Int = guess.charAt(i) - '0';
                                int c2Int = answer.charAt(i) - '0';
                                if (c1Int == c2Int) countA++;
                                else {
                                    guessArr[c1Int]++;
                                    answerArr[c2Int]++;
                                }
                            }
                            for (int i = 0; i < 10; i++) {
                                countB += Math.min(answerArr[i], guessArr[i]);
                            }
                            String realResult = countA + "A" + countB + "B";
                            if (!realResult.equals(expectResult)) {
                                count++;
                                break;
                            }
                        }
                        if (count == 0) cache.add(answer);
                    }
                }
            }
        }

        if (cache.size() != 1) System.out.println("NA");
        else System.out.println(cache.get(0));
    }
}

