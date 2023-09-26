package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2636 {
	static int N, M;
	static int[][] board;
	static boolean[][] v;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int chz = 0;
		int days = 0;
		board = new int[N][M];
		v = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) chz++;
			}
		}
		
		// 치즈가 모두 녹는데 걸리는 시간과 치즈가 모두 녹기 한시간 전 남아있던 치즈의 개수
		// 요점은 치즈에 나있는 구멍(1로 둘러싸인 0인 부분)을 공기로 보지 않는 것이다.
		
		// 공기 처리하기 - 2로 바꾸기
		bfs(0, 0);
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		
		// 공기에 맞닿은 치즈 처리하기
		
		while (true) {
			int remain = 0;
			days++;
			
			v = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 1) {
						v[i][j] = true;
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && board[nx][ny] == 2) {
								board[i][j] = 2;
								break;
							}
						}
					}
				}
			}
			
			v = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && board[nx][ny] == 2) {
								bfs(i, j);
								break;
							}
						}
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 1) remain++;
				}
			}
			
			if (remain == 0) {
				break;
			} else {
				chz = remain;
			}
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		System.out.println(days);
		System.out.println(chz);
	}
	
	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[x][y] = true;
		board[x][y] = 2;
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] q_poll = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = q_poll[0] + dx[i];
				int ny = q_poll[1] + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && board[nx][ny] == 0) {
					v[nx][ny] = true;
					board[nx][ny] = 2;
					q.offer(new int[] {nx, ny});
				}
			}
		} 
	}

}
