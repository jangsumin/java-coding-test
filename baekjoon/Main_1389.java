package baekjoon;

import java.io.*;
import java.util.*;

// 플로이드 워셜 함수 활용하기
public class Main_1389 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] rel = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) rel[i][j] = 0;
				else rel[i][j] = 999999;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int person1 = Integer.parseInt(st.nextToken()) - 1;
			int person2 = Integer.parseInt(st.nextToken()) - 1;
			
			rel[person1][person2] = 1;
			rel[person2][person1] = 1;
		}
		
		
//		for (int[] row : rel) System.out.println(Arrays.toString(row));
//		System.out.println();
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (rel[i][j] > rel[i][k] + rel[k][j]) rel[i][j] = rel[i][k] + rel[k][j];
				}
			}
		}
		
//		for (int[] row : rel) System.out.println(Arrays.toString(row));
		
		int min_result = Integer.MAX_VALUE;
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				sum += rel[i][j];
			}
			if (sum < min_result) {
				min_result = sum;
				result = i + 1;
			}
		}
		
		System.out.println(result);
	}

}
