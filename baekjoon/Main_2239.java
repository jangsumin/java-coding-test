package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2239 {
	static int[][] board = new int[9][9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String[] row = br.readLine().split("");
			for (int j = 0; j < 9; j++) board[i][j] = Integer.parseInt(row[j]);
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
		
		sudoku(0, 0);
		
	}
	
	static void sudoku(int row, int col) {
		if (col == 9) {
			sudoku(row + 1, 0);
			return;
		}
		if (row == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		if (board[row][col] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (check(row, col, i)) {
					board[row][col] = i;
					sudoku(row, col + 1);
				}
			}
			board[row][col] = 0;
			return;
		}
		sudoku(row, col + 1);
	}
	
	static boolean check(int row, int col, int v) {
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == v) return false;
		}
		
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == v) return false;
		}
		
		for (int i = (row / 3) * 3; i < ((row / 3) * 3) + 3; i++) {
			for (int j = (col / 3) * 3; j < ((col / 3) * 3) + 3; j++) {
				if (board[i][j] == v) return false;
			}
		}
		return true;
	}

}
