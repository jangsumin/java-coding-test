package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1113 {
	static int N, M;
	static int[][] board;
	static boolean[][][] v;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int total_water;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		int max_height = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(input[j]);
				max_height = Math.max(max_height, board[i][j]); // 최고층 수 구하기
			}
		}
		
		total_water = 0; // 총 물의 수, 점점 감소하는 형태로 정답 유도
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				total_water += max_height - board[i][j];
			}
		}
		
		v = new boolean[max_height][N][M];
		
		// 3차원의 방문 배열을 통해 수영장 구조 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int h = 0; h <= board[i][j] - 1; h++) {
					v[h][i][j] = true;
				}
			}
		}
		// 위에서부터 bfs
		for (int h = max_height - 1; h >= 1; h--) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!v[h][i][j]) {
						bfs(h, i, j);
					}
				}
			}
		}
		
		System.out.println(total_water);
	}
	
	// 16661
	// 61111
	// 16661

	// 테두리로 빠져나갈 수 있으면 층에 있는 물 모두 제거
	static void bfs(int h, int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {h, x, y});
		v[h][x][y] = true;
		boolean isPossible = true;
		int water_cnt = 1;
		while (!q.isEmpty()) {
			int[] q_poll = q.poll();
			int height = q_poll[0];
			for (int i = 0; i < 4; i++) {
				int nx = q_poll[1] + dx[i];
				int ny = q_poll[2] + dy[i];
				if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) isPossible = false; // 일단 벗어날 수 있기 시작할 때부터 틀림
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[height][nx][ny]) {
					q.offer(new int[] {height, nx, ny});
					v[height][nx][ny] = true;
					water_cnt++;
				}
			}
		}
		if (!isPossible) { // 틀린 경우에만 물을 빼냄
			total_water -= water_cnt;
		}
	}
}
