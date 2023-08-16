package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9663 {
	static int N;
	static int[] col;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N + 1];
		
		setQueen(1);
		System.out.println(answer);
		sc.close();
	}
	
	static void setQueen(int row) {
		if (!isPromising(row - 1)) return;
		if (row == N + 1) {
			answer++;
			return;
		}
		for (int i = 1; i <= N; i++) {
			col[row] = i;
			setQueen(row + 1);
		}
	}
	
	static boolean isPromising(int row) {
		for (int i = 1; i < row; i++) {
			if (col[i] == col[row] || row - i == Math.abs(col[row] - col[i])) return false;
		}
		return true;
	}

}
