// HSAT 7회 정기 코딩 인증평가 기출문제
package softeer;

import java.io.*;
import java.util.*;

public class Main_순서대로방문하기 {
  static int n, m;
  static int[][] board;
  static boolean[][] v;
  static int[][] points;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int result = 0;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    v = new boolean[n][n];

    // 보드 입력 받기
    board = new int[n][n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) board[i][j] = Integer.parseInt(st.nextToken());
    }
    // for (int[] row : board) System.out.println(Arrays.toString(row));

    // 지점 입력 받기
    points = new int[m][2];
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      points[i][0] = Integer.parseInt(st.nextToken()) - 1;
      points[i][1] = Integer.parseInt(st.nextToken()) - 1;
    }
    // for (int[] row : points) System.out.println(Arrays.toString(row));

    dfs(points[0][0], points[0][1], 0);

    System.out.println(result);
  }

  static void dfs(int x, int y, int cnt) {
    int next = cnt;
    if (points[next][0] == x && points[next][1] == y) next++;
    if (next == m) {
      result++;
      return;
    }
    
    v[x][y] = true;
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && ny >= 0 && nx < n && ny < n && !v[nx][ny] && board[nx][ny] == 0) {
        dfs(nx, ny, next);
      }
    }
    v[x][y] = false;
  }
}
