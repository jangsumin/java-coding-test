package baekjoon;

import java.io.*;
import java.util.*;

public class Main_3109 {
	static int R, C;
	static char[][] board;
	static final int[] dx = {-1, 0, 1};
	static int result = 0;
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String[] row = br.readLine().split("");
			for (int j = 0; j < C; j++) board[i][j] = row[j].charAt(0);
		}
		
		// for (char[] row : board) System.out.println(Arrays.toString(row));
		
		for (int i = 0; i < R; i++) {
			// 열의 0번째 행부터 R-1번째 행까지 시작점으로 잡고 출발
			if (start(i, 0)) result++;
		}
		
		System.out.println(result);
	}
	
	static boolean start(int row, int cnt) {
//		for (char[] rows : board) System.out.println(Arrays.toString(rows));
// 		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			int nx = row + dx[i];
			int ny = cnt + 1;
			if (ny == C - 1) {
				return true;
			}
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && board[nx][ny] != 'x') {
				board[nx][ny] = 'x';
				if (start(nx, ny)) return true;
			}
		}
		return false;
	}
}
