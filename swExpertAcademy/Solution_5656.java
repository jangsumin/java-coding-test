package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_5656 {
	static int N, W, H;
	static int[][] board;
	static int[][] new_board;
	static int[] save;
	static int result;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			board = new int[H][W];
			save = new int[N];
			result = Integer.MAX_VALUE;
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// for (int[] row : board) System.out.println(Arrays.toString(row));
			
			dup_perm(0, N, W);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void dup_perm(int cnt, int N, int W) {
		if (cnt == N) {
			// System.out.println(Arrays.toString(save));
			
			new_board = copy(board);
			for (int col : save) {
				drop(col);
			}
			
			int brick = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (new_board[i][j] >= 1) brick += 1;
				}
			}
			
			result = Math.min(result, brick);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			save[cnt] = i;
			dup_perm(cnt + 1, N, W);
		}
	}
	
	static void drop(int col) {
		for (int i = 0; i < H; i++) {
			if (new_board[i][col] != 0) {
				// System.out.println(i + " " + col);
				shoot(i, col, new_board[i][col]);
				break; // 가장 위의 벽돌을 터뜨리고 나면 끝
			}
		}
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (new_board[j][i] == 0) {
					int height = j - 1;
					while (true) {
						if (height < 0) break;
						if (new_board[height][i] >= 1) {
							new_board[j][i] = new_board[height][i];
							new_board[height][i] = 0;
							break;
						}
						height--;
					}
				}
			}
		}
	}
	
	static void shoot(int x, int y, int range) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, range});
		new_board[x][y] = 0;
		while(!q.isEmpty()) {
			int[] q_poll = q.poll();
			for (int r = 1; r < q_poll[2]; r++) { 
				for (int i = 0; i < 4; i++) { // 4방 탐색
					int nx = q_poll[0] + dx[i] * r;
					int ny = q_poll[1] + dy[i] * r;
					if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
						if (new_board[nx][ny] >= 1) {
							q.offer(new int[] {nx, ny, new_board[nx][ny]});
							new_board[nx][ny] = 0;
						}
					}
				}
			}
		}
	}
	
	static int[][] copy(int[][] arr) {
		int[][] new_arr = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				new_arr[i][j] = arr[i][j];
			}
		}
		return new_arr;
	}
	
}
