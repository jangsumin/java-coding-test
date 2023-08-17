package baekjoon;

import java.io.*;
import java.util.*;

public class Main_15684 {
	static int N, H, answer;
	static int[][] board;
	static boolean finished = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		board = new int[H][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			board[a][b] = 1;
			board[a][b + 1] = 2;
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		
		for (int i = 0; i <= 3; i++) {
			answer = i;
			dfs(0, 0);
			if (finished) break;
		}
		
		System.out.println((finished) ? answer : -1);
	}
	
	static void dfs(int idx, int cnt) {
		if (finished) return;
		if (answer == cnt) {
			if (check()) finished = true;
			return;
		}
		
		for (int x = idx; x < H; x++) {
			for (int y = 0; y < N - 1; y++) {
				if (board[x][y] == 0 && board[x][y + 1] == 0) {
					board[x][y] = 1;
					board[x][y + 1] = 2;
					dfs(x, cnt + 1);
					board[x][y] = 0;
					board[x][y + 1] = 0;
				}
			}
		}
	}
	
	static boolean check() {
		for (int y = 0; y < N; y++) {
			int i = 0, j = y;
			for (int x = 0; x < H; x++) {
				if (board[i][j] == 1) j++;
				else if (board[i][j] == 2) j--;
				i++;
			}
			if (j != y) return false;
		}
		return true;
	}

}
