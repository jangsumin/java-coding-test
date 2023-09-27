package baekjoon;

import java.io.*;
import java.util.*;

public class Main_3055 {
	static int R, C;
	static char[][] board;
	static int[][] s;
	static List<int[]> w_list = new ArrayList<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] v;
	static int sx, sy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		s = new int[R][C];
		v = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				board[i][j] = input[j].charAt(0);
				if (board[i][j] == '*') w_list.add(new int[] {i, j});
				if (board[i][j] == 'S') {
					sx = i;
					sy = j;
				}
			}
		}
		
		int result = bfs(w_list);
		if (result == -1) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	static int bfs(List<int[]> list) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for (int[] el : list) {
			v[el[0]][el[1]] = true;
			q.offer(new int[] {el[0], el[1], 0}); // 마지막 원소가 0일 경우 물에 대해 고려하기
		}
		q.offer(new int[] {sx, sy, 1}); // 1인 경우는 고슴도치에 대해 고려하기
		while (!q.isEmpty()) {
			int[] q_poll = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = q_poll[0] + dx[i];
				int ny = q_poll[1] + dy[i];
				if (q_poll[2] == 0) {
					if (nx >= 0 && nx < R && ny >= 0 && ny < C && !v[nx][ny] && board[nx][ny] != 'D' && board[nx][ny] != 'X') {
						v[nx][ny] = true;
						q.offer(new int[] {nx, ny, 0});
					}					
				} else {
					if (nx >= 0 && nx < R && ny >= 0 && ny < C && !v[nx][ny] && board[nx][ny] != 'X') {
						if (board[nx][ny] == 'D') return s[q_poll[0]][q_poll[1]] + 1;
						v[nx][ny] = true;
						s[nx][ny] = s[q_poll[0]][q_poll[1]] + 1;
						q.offer(new int[] {nx, ny, 1});
					}
				}
			}
		}
		
		return -1;
	}
}
