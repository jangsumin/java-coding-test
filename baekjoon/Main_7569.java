package baekjoon;

import java.io.*;
import java.util.*;

public class Main_7569 {
	static int M, N, H;
	static int[][][] board;
	static boolean[][][] v;
	static final int[] dh = {0, 0, 0, 0, -1, 1}; // 평면 상에서 상 우 하 좌 위층 아래층
	static final int[] dx = {-1, 0, 1, 0, 0, 0};
	static final int[] dy = {0, 1, 0, -1, 0, 0};

	public static void main(String[] args) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new int[H][N][M];
		v = new boolean[H][N][M];
		// 가장 밑의 상자에서부터 위의 상자까지
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					board[h][i][j] = Integer.parseInt(st.nextToken());
					if (board[h][i][j] == 1) q.offer(new int[] {h, i, j});
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] q_poll = q.poll();
			for (int i = 0; i < 6; i++) {
				int nh = q_poll[0] + dh[i];
				int nx = q_poll[1] + dx[i];
				int ny = q_poll[2] + dy[i];
				
				if (nh >= 0 && nh < H && nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nh][nx][ny] && board[nh][nx][ny] == 0) {
					q.offer(new int[] {nh, nx, ny});
					v[nh][nx][ny] = true;
					board[nh][nx][ny] = board[q_poll[0]][q_poll[1]][q_poll[2]] + 1;
				}
			}
		}
		
		int result = 0;
		boolean isPossible = true;
		next: for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[h][i][j] == 0) {
						isPossible = false;
						break next;
					}
					result = Math.max(result, board[h][i][j]);
				}
			}
		}
		if (isPossible) System.out.println(result - 1);
		else System.out.println(-1);
	}

}
