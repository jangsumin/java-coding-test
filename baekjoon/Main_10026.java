package baekjoon;

import java.io.*;
import java.util.*;

public class Main_10026 {
	static int N;
	static char[][] board;
	static char[][] new_board;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		v = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] row = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				board[i][j] = row[j].charAt(0);
			}
		}
		
		
//		for (char[] row : board) System.out.println(Arrays.toString(row));
//		System.out.println();
//		for (char[] row : new_board) System.out.println(Arrays.toString(row));
		
		int cnt1 = 0;
		int cnt2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((board[i][j] == 'R' || board[i][j] == 'G' || board[i][j] == 'B') && !v[i][j]) {
					bfs(i, j, board[i][j]);
					cnt1 += 1;
				}
			}
		}
		
		// System.out.println(cnt1);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'G') board[i][j] = 'R';
			}
		}
		
		v = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((board[i][j] == 'R' || board[i][j] == 'B') && !v[i][j]) {
					bfs(i, j, board[i][j]);
					cnt2 += 1;
				}
			}
		}
		
		System.out.print(cnt1 + " " + cnt2);
	}
	
	static void bfs(int x, int y, char color) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[x][y] = true;
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int poll_x = temp[0];
			int poll_y = temp[1];
			for (int i = 0; i < 4; i++) {
				int nx = poll_x + dx[i];
				int ny = poll_y + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !v[nx][ny] && board[nx][ny] == color) {
					v[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

}
