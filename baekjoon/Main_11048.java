package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11048 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] v = new boolean[N][M];
		int[][] save = new int[N][M];
		v[0][0] = true;
		save[0][0] = board[0][0];
		for (int i = 1; i < N; i++) {
			save[i][0] += board[i][0] + save[i - 1][0];
		}
		
		for (int j = 1; j < M; j++) {
			save[0][j] += board[0][j] + save[0][j - 1];
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				int max = Math.max(save[i - 1][j], save[i][j - 1]);
				save[i][j] = max + board[i][j];
			}
		}
		
		System.out.println(save[N - 1][M - 1]);
	}

}
