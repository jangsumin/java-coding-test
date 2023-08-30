package baekjoon;

import java.io.*;
import java.util.*;

public class Main_10971 {
	static int N;
	static int[][] matrix;
	static boolean[] v;
	static int[] save;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		v = new boolean[N];
		save = new int[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0);
		System.out.println(result);
	}
	
	static void perm(int cnt) {
		if (cnt == N) {
			System.out.println(Arrays.toString(save));
			if (check(save)) {
				int money = calculate(save);
				result = Math.min(result, money);
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (v[i]) continue;
			save[cnt] = i;
			v[i] = true;
			perm(cnt + 1);
			v[i] = false;
		}
	}
	
	static boolean check(int[] save) {
		for (int i = 0; i < N - 1; i++) {
			if (matrix[save[i]][save[i + 1]] == 0) {
				return false;
			}
		}
		if (matrix[save[N - 1]][save[0]] == 0) return false;
		
		return true;
	}
	
	static int calculate(int[] save) {
		int cost = 0;
		for (int i = 0; i < N - 1; i++) {
			cost += matrix[save[i]][save[i + 1]];
		}
		cost += matrix[save[N - 1]][save[0]];
		return cost;
	}

}
