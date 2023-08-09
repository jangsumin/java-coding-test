package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14940 {
	static int N, M;
	static int[][] map;
	static boolean[][] v;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2 && !v[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (v[i][j] == false && map[i][j] != 0) map[i][j] = -1;
				if (j == M - 1) sb.append(map[i][j]);
				else sb.append(map[i][j]).append(" ");
			}
			if (i != N - 1) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		map[x][y] = 0;
		v[x][y] = true;
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int xx = poll[0];
			int yy = poll[1];
			for (int i = 0; i < 4; i++) {
				int nx = xx + dx[i];
				int ny = yy + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 1 && !v[nx][ny]) {
					map[nx][ny] = map[xx][yy] + 1;
					v[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

}
