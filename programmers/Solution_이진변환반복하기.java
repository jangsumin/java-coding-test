package programmers;

import java.io.*;
import java.util.*;

public class Solution_이진변환반복하기 {
  public int[] solution(String s) {
        int[] answer = new int[2];
        
        int changeCnt = 0;
        int removedZeroCnt = 0;
        String[] arr = s.split("");
        
        while (true) {
            if (arr.length == 1) break;
            
            String newStr = "";
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("1")) newStr += "1";
                else removedZeroCnt++;
            }
            
            changeCnt++;
            newStr = Integer.toBinaryString(newStr.length());
            arr = newStr.split("");
        }
        
        answer[0] = changeCnt;
        answer[1] = removedZeroCnt;
        return answer;
    }
}