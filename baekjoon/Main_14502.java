package a0925.camp;

import java.io.*;
import java.util.*;

public class Main_14502 {
	static int N, M;
	static int[][] board;
	static int[][] new_board;
	static boolean[][] v;
	static List<int[]> list = new ArrayList<>();
	static int[][] save = new int[3][2];
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int max_cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		new_board = new int[N][M];
		v = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		
		comb(0, 0);
		
		System.out.println(max_cnt);
	}
	
	static void comb(int cnt, int start) {
		if (cnt == 3) {
			int safety = 0;
			new_board = copy(board);
			v = new boolean[N][M];
			for (int i = 0; i < 3; i++) {
				new_board[save[i][0]][save[i][1]] = 1;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (new_board[i][j] == 2 && !v[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (new_board[i][j] == 0) safety += 1;
				}
			}
			
			max_cnt = Math.max(safety, max_cnt);
			
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			save[cnt][0] = list.get(i)[0];
			save[cnt][1] = list.get(i)[1];
			comb(cnt + 1, i + 1);
		}
	}
	
	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[x][y] = true;
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] poll_q = q.poll();
			int px = poll_q[0];
			int py = poll_q[1];
			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && new_board[nx][ny] == 0) {
					v[nx][ny] = true;
					new_board[nx][ny] = 2;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	static int[][] copy(int[][] board) {
		int[][] new_board = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				new_board[i][j] = board[i][j];
			}
		}
		return new_board;
	}
	
}
