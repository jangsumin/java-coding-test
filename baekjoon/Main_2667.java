package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2667 {
	static int N, cnt = 0;
	static int[][] map;
	static boolean[][] v;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] row = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		int town = 0;
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !v[i][j]) {
					town += 1;
					cnt = 0;
					dfs(i, j);
					result.add(cnt);
				}
			}
		}
		
		Collections.sort(result);
		System.out.println(town);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		br.close();
	}
	
	static void dfs(int x, int y) {
		v[x][y] = true;
		cnt += 1;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !v[nx][ny] && map[nx][ny] == 1) {
				dfs(nx, ny);
			}
		}
	}

}
