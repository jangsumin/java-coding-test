package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2630 {
	static int[][] paper;
	static int[] result = {0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		devided(0, 0, N);
		
		// for (int[] row : paper) System.out.println(Arrays.toString(row));
		
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
	
	static void devided(int x, int y, int n) {
		// System.out.println(x + " " + y + " " + n);
		int curr = paper[x][y];
		
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (paper[i][j] != curr) {
					devided(x, y, n / 2);
					devided(x, y + n / 2, n / 2);
					devided(x + n / 2, y, n / 2);
					devided(x + n / 2, y + n / 2, n / 2);
					return;
				}
			}
		}
		result[curr] += 1;
	}

}
