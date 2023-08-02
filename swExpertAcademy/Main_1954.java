package swExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1954 {
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			int cnt = N - 1;
			int i = 1;
			int start = 0;
			int end = N - 1;
			// [0][0] -> [0][1] -> [0][2] -> [0][3] -> [1][3]...
			if (N != 1) {
				next:while (true) {
					for (int j = 0; j < cnt; j++) {
						map[start][start + j] = i++;
						if (i == N * N) break next;
					}
					for (int j = 0; j < cnt; j++) {
						map[start + j][end] = i++;
						if (i == N * N) break next;
					}
					for (int j = 0; j < cnt; j++) {
						map[end][end - j] = i++;
						if (i == N * N) break next;
					}
					for (int j = 0; j < cnt; j++) {
						map[end - j][start] = i++;
						if (i == N * N) break next;
					}
					cnt -= 2;
					start++;
					end--;
				}
			}
			
			if (N % 2 == 1) map[N / 2][N / 2] = N * N;
			else map[N / 2][N / 2 - 1] = N * N;
			
			System.out.printf("#%d\n", tc);
			for (int[] row : map) {
				StringBuilder sb = new StringBuilder();
				for (int el : row) sb.append(el).append(' ');
				System.out.println(sb.toString());
			}
		}

	}
}
