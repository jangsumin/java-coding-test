package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1600 {
	static int K, W, H;
	static int[][] board;
	static boolean[][][] v;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static final int[] dhx = {-1, -2, -2, -1, 1, 2, 2, 1}; // 말이 이동하는 위치 
	static final int[] dhy = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		v = new boolean[K + 1][H][W]; // 방문 배열을 3차원으로 만들지 않고 2차원으로 압축하기
		board = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) board[i][j] = -1;
			}
		}
		
		
		System.out.println(bfs(0, 0));
		// for (int[] row : board) System.out.println(Arrays.toString(row));
	}

	static int bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, 0, 0});
		for (int i = 0; i <= K; i++) v[i][0][0] = true;
		while(!q.isEmpty()) {
			int[] q_poll = q.poll();
			// System.out.println(Arrays.toString(q_poll));
			
			if (q_poll[0] == H - 1 && q_poll[1] == W - 1) return q_poll[3];
			
			if (q_poll[2] < K) {
				for (int i = 0; i < 8; i++) {
					int nx = q_poll[0] + dhx[i];
					int ny = q_poll[1] + dhy[i]; 
					if (nx >= 0 && nx < H && ny >= 0 && ny < W && !v[q_poll[2] + 1][nx][ny] && board[nx][ny] != -1) {
						v[q_poll[2] + 1][nx][ny] = true;
						q.offer(new int[] {nx, ny, q_poll[2] + 1, q_poll[3] + 1});
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = q_poll[0] + dx[i];
				int ny = q_poll[1] + dy[i]; 
				if (nx >= 0 && nx < H && ny >= 0 && ny < W && !v[q_poll[2]][nx][ny] && board[nx][ny] != -1) {
					v[q_poll[2]][nx][ny] = true;
					q.offer(new int[] {nx, ny, q_poll[2], q_poll[3] + 1});
				}
			}
		}
		
		return -1;
	}
}
