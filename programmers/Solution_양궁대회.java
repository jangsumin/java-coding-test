package programmers;

import java.io.*;
import java.util.*;

// 어피치보다 화살 개수를 한 개만 더 쏴서 점수 가져오기
// [3, 2, 2, 2, 1, 1, 1, 1, 1, 1] -> 여기서 가능한 모든 부분 집합을 생성해서 5보다 같거나 작으면 점수 계산하기

class Solution_양궁대회 {
    static int[] ryan_condition = new int[10]; // 각 점수마다 라이언이 이겨야 하는 화살 개수 조건을 담는 배열
    static boolean[] isSelected = new boolean[10];
    static int score_diff = -55; // 라이언 점수에서 어피치 점수를 뺀 값으로, 가장 작은 값은 -55
    static int[] result = new int[11];
    static int[] not_win = {-1}; // 이길 수 없을 때 반환하는 배열
    
    public int[] solution(int n, int[] info) {
        // 마지막 0점은 몇 개를 넣어도 상관없으므로 제외
        for (int i = 0; i < 10; i++) ryan_condition[i] = info[i] + 1;
        
        subset(0, n, info);
        
        if (score_diff <= 0) return not_win;
        else return result;
    }
    
    // 재귀를 통한 부분 집합 생성
    public void subset(int cnt, int limit, int[] apeach_game) {
        if (cnt == 10) { // isSelected 배열 활용해서 ryan과 apeach의 점수를 구하기
            int arrow_cnt = 0;
            int ryan_score = 0;
            int apeach_score = 0;
            int[] ryan_game = new int[11];
            
            for (int i = 0; i < 10; i++) {
                if (isSelected[i]) {
                    arrow_cnt += ryan_condition[i];
                    ryan_game[i] = ryan_condition[i];
                    ryan_score += 10 - i;
                }
                if (!isSelected[i] && apeach_game[i] > 0) {
                    apeach_score += 10 - i;
                }
            }
            
            if (arrow_cnt < limit) ryan_game[10] = limit - arrow_cnt;
            if (arrow_cnt <= limit) {
                if (ryan_score - apeach_score == score_diff) {
                    result = checkArr(result, ryan_game).clone();
                    score_diff = ryan_score - apeach_score;
                } else if(ryan_score - apeach_score > score_diff) {
                    result = ryan_game.clone();
                    score_diff = ryan_score - apeach_score;
                }
            }
        } else { // 재귀를 통해 isSelected에 true 혹은 false 값 저장
            isSelected[cnt] = true;
            subset(cnt + 1, limit, apeach_game);
            isSelected[cnt] = false;
            subset(cnt + 1, limit, apeach_game);
        }
    }
    
    public int[] checkArr(int[] a, int[] b) {
        for(int i = a.length - 1; i >= 0; i--) {
            if(a[i] == b[i]) continue;
            if(a[i] < b[i]) return b;
            break;
        }
        return a;
    }

}
