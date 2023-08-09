package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1861 {
	static int N;
	static int[][] map;
	static boolean[][] v;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			// map에 row 할당
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int start = N * N;
			int max_move = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					v = new boolean[N][N];
					int move = dfs(i, j);
					// System.out.println(move);
//					if (move >= max_move && map[i][j] < start) {
//						start = map[i][j];
//						max_move = move;
//					}
					if (move > max_move) {
						start = map[i][j];
						max_move = move;
					} else if (move == max_move && map[i][j] < start) {
						start = map[i][j];
					}
				}
			}
			
			System.out.println(String.format("#%d %d %d", tc, start, max_move));
		}
	}
	
	// dfs int로 반환하기
	static int dfs(int x, int y) {
		int cnt = 1;
		v[x][y] = true;
		 
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !v[nx][ny] && map[nx][ny] == map[x][y] + 1) {
				cnt += dfs(nx, ny);
			}
		}
		
		return cnt;
	}
}
