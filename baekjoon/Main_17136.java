package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17136 {
	static int[][] board = new int[10][10];
	static int[] paper_quantities = {0, 5, 5, 5, 5, 5};
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		
		dfs(0, 0, 0);
		
		if (result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
				
		br.close();
	}
	
	static void dfs(int row, int col, int cnt) {
		if (row == 9 && col > 9) {
			result = Math.min(result, cnt);
			return;
		}
		
		if (result <= cnt) return;
		
		if (col > 9) {
			dfs(row + 1, 0, cnt);
			return;
		}
		
		if (board[row][col] == 1) {
			for (int i = 1; i <= 5; i++) {
				if (paper_quantities[i] > 0 && isAttachable(row, col, i)) {
					paper_quantities[i]--;
					attach(row, col, i, 0);
					dfs(row, col + 1, cnt + 1);
					paper_quantities[i]++;
					attach(row, col, i, 1);
				}
			}
		} else {
			dfs(row, col + 1, cnt);
		}
	}
	
	static void attach(int x, int y, int len, int state) {
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				board[i][j] = state;
			}
		}
	}
	
	static boolean isAttachable(int x, int y, int len) {
		for (int i = x; i < x + len; i++) {
			for (int j = y; j < y + len; j++) {
				if (i > 9 || j > 9) return false;
				if (board[i][j] == 0) return false;
			}
		}
		return true;
	}

}
