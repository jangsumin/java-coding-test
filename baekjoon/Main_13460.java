package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13460 {
	static int N, M;
	static char[][] board;
	static boolean[][] v;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int red_x = 0, red_y = 0, blue_x = 0, blue_y = 0;
	static int min_result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		v = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				board[i][j] = input[j].charAt(0);
				if (board[i][j] == 'R') { // 빨간 공 위치 구하고, 빈 보드로 바꾸기
					red_x = i;
					red_y = j;
					board[i][j] = '.';
				}
				if (board[i][j] == 'B') {
					blue_x = i;
					blue_y = j;
					board[i][j] = '.';
				}		
			}
		}
		
		// for (char[] row : board) System.out.println(Arrays.toString(row));
		
		dfs(0);
		if (min_result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min_result);
	}
	
	static void dfs(int cnt) {
		if (cnt == 10) return;
		
		int tmp_red_x = red_x;
		int tmp_red_y = red_y;
		int tmp_blue_x = blue_x;
		int tmp_blue_y = blue_y;
		for (int i = 0; i < 4; i++) {
			tilt(i);
			if (board[blue_x][blue_y] == 'O') {
				red_x = tmp_red_x;
				red_y = tmp_red_y;
				blue_x = tmp_blue_x;
				blue_y = tmp_blue_y;
				continue;
			}
			if (board[red_x][red_y] == 'O') {
				min_result = Math.min(min_result, cnt + 1);
				break;
			}
			dfs(cnt + 1);
			red_x = tmp_red_x;
			red_y = tmp_red_y;
			blue_x = tmp_blue_x;
			blue_y = tmp_blue_y;
		}
	}
	
	static void tilt (int d) {
		int red_move = 0;
		int blue_move = 0;
		while (true) {
			int nx = red_x + dx[d];
			int ny = red_y + dy[d];
			if (board[nx][ny] == '#') break;
			red_x = nx;
			red_y = ny;
			red_move++;
			if (board[nx][ny] == 'O') break;
		}
		while (true) {
			int nx = blue_x + dx[d];
			int ny = blue_y + dy[d];
			if (board[nx][ny] == '#') break;
			blue_x = nx;
			blue_y = ny;
			blue_move++;
			if (board[nx][ny] == 'O') break;
		}
		if (red_x == blue_x && red_y == blue_y) { // 같은 지점에 있음
			if (board[red_x][red_y] == 'O') return;
			else if (red_move > blue_move) {
				red_x -= dx[d];
				red_y -= dy[d];
			} else {
				blue_x -= dx[d];
				blue_y -= dy[d];
			}
		}
	}

}
