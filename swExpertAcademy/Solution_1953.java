package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1953 {
	static int N, M, R, C, L;
	static int[][] board;
	static boolean[][] v;
	static int[] dx;
	static int[] dy;
	static int[][] allow;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			board = new int[N][M];
			v = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			 
			// for (int[] row : board) System.out.println(Arrays.toString(row));
			
			bfs(R, C, board, 1);
			
			// for (boolean[] row : v) System.out.println(Arrays.toString(row));
			
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (v[i][j] == true) result++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs(int x, int y, int[][] board, int cnt) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, board[x][y], cnt}); // 구조물 타입도 같이 입력하기
		v[x][y] = true;
		while(!q.isEmpty()) {
			int[] q_poll = q.poll();
			if (q_poll[3] == L) break;
			
			if (q_poll[2] == 1) { // 구조물 타입에 따라 방향 다르게 설정
				dx = new int[] {-1, 0, 1, 0};
				dy = new int[] {0, 1, 0, -1};
				allow = new int[][] {{1, 2, 5, 6}, {1, 3, 6, 7}, {1, 2, 4, 7}, {1, 3, 4, 5}};
			} else if (q_poll[2] == 2) {
				dx = new int[] {-1, 1};
				dy = new int[] {0, 0};
				allow = new int[][] {{1, 2, 5, 6}, {1, 2, 4, 7}};
			} else if (q_poll[2] == 3) {
				dx = new int[] {0, 0};
				dy = new int[] {1, -1};
				allow = new int[][] {{1, 3, 6, 7}, {1, 3, 4, 5}};
			} else if (q_poll[2] == 4) {
				dx = new int[] {-1, 0};
				dy = new int[] {0, 1};
				allow = new int[][] {{1, 2, 5, 6}, {1, 3, 6, 7}};
			} else if (q_poll[2] == 5) {
				dx = new int[] {1, 0};
				dy = new int[] {0, 1};
				allow = new int[][] {{1, 2, 4, 7}, {1, 3, 6, 7}};
			} else if (q_poll[2] == 6) {
				dx = new int[] {1, 0};
				dy = new int[] {0, -1};
				allow = new int[][] {{1, 2, 4, 7}, {1, 3, 4, 5}};
			} else if (q_poll[2] == 7) {
				dx = new int[] {-1, 0};
				dy = new int[] {0, -1};
				allow = new int[][] {{1, 2, 5, 6}, {1, 3, 4, 5}};
			}
			
			for (int i = 0; i < dx.length; i++) { // dx, dy의 길이는 언제나 같음
				int nx = q_poll[0] + dx[i];
				int ny = q_poll[1] + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny]) {
					for (int type : allow[i]) {
						if (board[nx][ny] == type) { // 가능한 타입이 있다면 고려하기
							v[nx][ny] = true;
							q.offer(new int[] {nx, ny, board[nx][ny], q_poll[3] + 1});
						}
					}
				}
			}
		}
	}

}
