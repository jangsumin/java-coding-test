package baekjoon;

import java.io.*;
import java.util.*;

public class Main_18428 {
	static int N;
	static char[][] board;
	static char[][] new_board;
	static List<int[]> list = new ArrayList<>();
	static int[][] save = new int[3][2];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static String result = "NO";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		new_board = new char[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = st.nextToken().charAt(0);
				if (board[i][j] == 'X') list.add(new int[] {i, j});
			}
		}
		
		// for (char[] row : board) System.out.println(Arrays.toString(row));
		// for (int[] el : list) System.out.println(Arrays.toString(el));
		
		comb(0, 0);
		System.out.println(result);
	}
	
	static void comb(int cnt, int start) {
		if (cnt == 3) {
			if (check(save)) result = "YES";
			return;
		}
		
		for (int i = start; i < list.size(); i++) {
			save[cnt][0] = list.get(i)[0];
			save[cnt][1] = list.get(i)[1];
			comb(cnt + 1, i + 1);
		}
	}
	
	static boolean check(int[][] save) {
		new_board = copy(board);
		for (int i = 0; i < 3; i++) {
			new_board[save[i][0]][save[i][1]] = 'O'; // 새로운 보드에 장애물 설치하기
		}
		
		// for (char[] row : new_board) System.out.println(Arrays.toString(row));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (new_board[i][j] == 'T') {
					for (int k = 0; k < 4; k++) { // 상하좌우로 움직이다가 학생 나오면 실패
						int move = 1; // 한칸 옮기는 것부터 시작
						while (i + dx[k] * move >= 0 && i + dx[k] * move < N && j + dy[k] * move >= 0 && j + dy[k] * move < N) {
							if (new_board[i + dx[k] * move][j + dy[k] * move] == 'O') break;
							if (new_board[i + dx[k] * move][j + dy[k] * move] == 'T') break;
							if (new_board[i + dx[k] * move][j + dy[k] * move] == 'S') return false;
							move++;
						}
					}
				}
			}
		}
		return true;
	}
	
	static char[][] copy(char[][] board) {
		char[][] new_board = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				new_board[i][j] = board[i][j];
			}
		}
		return new_board;
	}

}
