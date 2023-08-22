package baekjoon;

import java.io.*;
import java.util.*;

public class Main_15683 {
	static int N, M;
	static int[][] board;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int answer = Integer.MAX_VALUE;
	static int R;
	static List<int[]> cctv_list = new ArrayList<>();
	static int[] save;
	static int[][] new_board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		new_board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1 || board[i][j] == 2 || board[i][j] == 3 || board[i][j] == 4 || board[i][j] == 5) {
					cctv_list.add(new int[] {i, j, board[i][j]});
				}
			}
		}
		
		R = cctv_list.size();
		save = new int[cctv_list.size()];
		
		// for (int[] el : cctv_list) System.out.println(Arrays.toString(el));
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				
//			}
//		}
		
		perm(0);
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		System.out.println(answer);
	}
	
	// 0: 위, 1: 오른, 2: 아래, 3: 왼
	// 1번의 경우 4가지 [[0], [1], [2], [3]]
	// 2번의 경우 2가지 [[0, 2], [1, 3]]
	// 3번의 경우 4가지 [[0, 1], [1, 2], [2, 3], [3, 0]]
	// 4번의 경우 4가지 [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]]
	// 5번의 경우 1가지 [[0, 1, 2 ,3]]
	
	static void perm(int cnt) {
		if (cnt == R) {
			new_board = copyArray(board);
			for (int i = 0; i < R; i++) {
				int[] info = cctv_list.get(i); // [1, 1, 2]
				inspect(info, save[i]);
			}
			
			int notSeen = 0;
			
			// 사각지대 구하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (new_board[i][j] == 0) notSeen += 1;
				}
			}
			
			answer = Math.min(answer, notSeen);
			
			// System.out.println(Arrays.toString(save));
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			save[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	static void inspect(int[] info, int d) {
		if (info[2] == 1) {
			if (d == 0) check(info, 0);
			if (d == 1) check(info, 1);
			if (d == 2) check(info, 2);
			if (d == 3) check(info, 3);
		} else if (info[2] == 2) {
			if (d == 0 || d == 2) {
				check(info, 0);
				check(info, 2);
			} else {
				check(info, 1);
				check(info, 3);
			}
		} else if (info[2] == 3) {
			if (d == 0) {
				check(info, 0);
				check(info, 1);
			} else if (d == 1) {
				check(info, 1);
				check(info, 2);
			} else if (d == 2) {
				check(info, 2);
				check(info, 3);
			} else if (d == 3) {
				check(info, 3);
				check(info, 0);
			}
		} else if (info[2] == 4) {
			if (d == 0) {
				check(info, 0);
				check(info, 1);
				check(info, 2);				
			} else if (d == 1) {
				check(info, 1);
				check(info, 2);
				check(info, 3);
			} else if (d == 2) {
				check(info, 2);
				check(info, 3);
				check(info, 0);
			} else if (d == 3) {
				check(info, 3);
				check(info, 0);
				check(info, 1);
			}
		} else if (info[2] == 5) {
			check(info, 0);
			check(info, 1);
			check(info, 2);
			check(info, 3);
		}
	}
	
	static void check(int[] info, int d) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		
		q.add(info);
		v[info[0]][info[1]] = true;
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int nx = tmp[0] + dx[d];
			int ny = tmp[1] + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || new_board[nx][ny] == 6) break;
			
			if (new_board[nx][ny] == 0) {
				new_board[nx][ny] = -1;
				q.add(new int[] {nx, ny, info[2]});
			} else {
				q.add(new int[] {nx, ny, info[2]});
			}
		}
	}
	
	static int[][] copyArray(int[][] board) {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = board[i][j];
			}
		}
		return temp;
	}

}
