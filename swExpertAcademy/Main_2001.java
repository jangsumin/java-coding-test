package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Main_2001 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			int start = M;
			
			for (int i = 0; i < N - start + 1; i++) {
				for (int j = 0; j < N - start + 1; j++) {
					int sum = 0;
					for (int k = 0; k < start; k++) {
						for (int m = 0; m < start; m++) {
							sum += map[i + k][j + m];
						}
					}
					// System.out.println(sum);
					if (sum > max) max = sum;
				}
			}
				
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

}
