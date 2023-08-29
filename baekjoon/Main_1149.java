package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1149 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] save = new int[N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				save[i][j] = Integer.MAX_VALUE;
			}
		}
		save[0][0] = rgb[0][0];
		save[0][1] = rgb[0][1];
		save[0][2] = rgb[0][2];
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (j == k) continue;
					save[i][j] = Math.min(save[i][j], rgb[i][j] + save[i - 1][k]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			result = Math.min(result, save[N - 1][i]);
		}
		
		System.out.println(result);
	}

}
