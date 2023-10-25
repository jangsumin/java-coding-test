import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        
        Map<String, Integer> dict = new HashMap<>();
        int num = 1;
        char alphabet = 'A';
        for (int i = 0; i < 26; i++) {
            dict.put(Character.toString(alphabet++), num++);
        }
        
        int i = 0;
        while (i < msg.length()) {
            int j = 1;
            String str = "" + msg.charAt(i); // K
            
            while (true) {
                if (i + j >= msg.length()) { // "KAKAO"에서 O의 경우 고려하기
                    result.add(dict.get(str));
                    break;
                }
                
                if (!dict.containsKey(str + msg.charAt(i + j))) { // KA
                    result.add(dict.get(str)); // 11
                    dict.put(str + msg.charAt(i + j), num++); // KA : 27

                    break;
                }
                
                str += msg.charAt(i + j++);
            }
            
            i += j;
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
