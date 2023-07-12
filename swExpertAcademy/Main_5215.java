package swExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5215 {
  static int[] hbg_score;
  static int[] hbg_cal;
  static int max_score = 0;
  static int N, L;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      N = Integer.parseInt(st.nextToken());
      L = Integer.parseInt(st.nextToken());
      hbg_score = new int[N];
      hbg_cal = new int[N];
      max_score = 0;

      // 햄버거의 점수, 칼로리 입력
      for (int hbg = 0; hbg < N; hbg++) {
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        hbg_score[hbg] = Integer.parseInt(st1.nextToken());
        hbg_cal[hbg] = Integer.parseInt(st1.nextToken());
      }

      /*
       * 햄버거가 5개라고 가정하면
       * 1, 2, 3, 4, 5, (1, 2), (1, 3), (1, 4), (1, 5)
       * ..., (2, 4, 5), (3, 4, 5), (2, 3, 4, 5), (1, 2, 3, 4, 5)
       * 이렇게 조합을 계속 구해서 칼로리 범위 내에서 가장 높은 점수를 갱신해야 함.
       */

      combination(0, 0, 0);

      System.out.println(String.format("%s%d %d", "#", tc, max_score));
    }
  }

  static void combination(int start, int score, int cal) {
    // 이미 제한 칼로리를 넘었을 때
    if (cal > L) return;
    if (cal <= L) {
      if (score > max_score) {
        max_score = score;
      }
    }
    if (start == N) return;

    combination(start + 1, score + hbg_score[start], cal + hbg_cal[start]);
    combination(start + 1, score, cal);
  }
}