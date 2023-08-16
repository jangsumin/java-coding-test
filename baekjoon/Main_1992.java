package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1992 {
	static int N;
	static int[][] board;
	static String result = "";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		divide(0, 0, N);
		System.out.println(result);
	}
	
	static void divide(int x, int y, int n) {
		int curr = board[x][y];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[x + i][y + j] != curr) {
					result += "(";
					divide(x, y, n / 2);
					divide(x, y + n / 2, n / 2);
					divide(x + n / 2, y, n / 2);
					divide(x + n / 2, y + n / 2, n / 2);
					result += ")";
					return;
				}
			}
		}
		
		result += curr;
	}

}
