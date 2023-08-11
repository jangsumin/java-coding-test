package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14889 {
	static int N;
	static int[][] sy;
	static boolean[] select;
	static int min_diff = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sy = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				sy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		select = new boolean[N];
		
		comb(0, 0);
		
		System.out.println(min_diff);
	}
	
	static void comb(int cnt, int start) {
		if (cnt == N / 2) {
			int start_sum = 0;
			int link_sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					if (select[i] && select[j]) start_sum += sy[i][j];
					if (!select[i] && !select[j]) link_sum += sy[i][j];
				}
			}
			if (Math.abs(start_sum - link_sum) < min_diff) min_diff = Math.abs(start_sum - link_sum);
			return;
		}
		
		for (int i = start; i < N; i++) {
			select[i] = true;
			comb(cnt + 1, i + 1);
			select[i] = false;
		}
	}

}
