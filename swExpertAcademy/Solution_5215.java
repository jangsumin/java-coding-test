import java.io.*;
import java.util.*;
 
public class Solution_5215 {
    static int N, L;
    static int[] score, kcal;
    static boolean[] v;
    static int max_score = 0;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
             
            // 인덱스 그대로 유지
            max_score = 0;
            score = new int[N];
            kcal = new int[N];
            v = new boolean[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                score[i] = Integer.parseInt(st.nextToken());
                kcal[i] = Integer.parseInt(st.nextToken());
            }
             
            subset(0);
            System.out.println(String.format("#%d %d", tc, max_score));
        }
    }
     
    static void subset(int cnt) {
        if (cnt == N) {
            int kcal_sum = 0;
            int score_sum = 0;
            for (int i = 0; i < N; i++) {
                if (v[i]) {
                    kcal_sum += kcal[i];
                    score_sum += score[i];
                }
            }
            if (kcal_sum <= L && score_sum > max_score) max_score = score_sum;
            return;
        }
         
        v[cnt] = true;
        subset(cnt + 1);
        v[cnt] = false;
        subset(cnt + 1);
    }
 
}
