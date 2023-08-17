import java.util.*;
import java.io.*;

public class Solution_1873 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			
			char[][] board = new char[H][W];
			for (int i = 0; i < H; i++) {
				String[] row = br.readLine().split("");
				for (int j = 0; j < W; j++) {
					board[i][j] = row[j].charAt(0);
				}
			}
			int N = Integer.parseInt(br.readLine());
			String[] command = br.readLine().split("");
			
			// for (char[] row : board) System.out.println(Arrays.toString(row));
			
			int[] location = new int[2];
			char tank = ' ';
			next: for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (board[i][j] == '<' || board[i][j] == '^' || board[i][j] == '>' || board[i][j] == 'v') {
						location[0] = i;
						location[1] = j;
						tank = board[i][j];
						break next;
					}
				}
			}
			
			for (int i = 0; i < command.length; i++) {
				if (command[i].equals("S")) {
					if (tank == '<') {
						for (int j = location[1] - 1; j >= 0; j--) {
							if (board[location[0]][j] == '*') {
								board[location[0]][j] = '.';
								break;
							}
							if (board[location[0]][j] == '#') break;
						}
					}
					if (tank == '>') {
						for (int j = location[1] + 1; j < W; j++) {
							if (board[location[0]][j] == '*') {
								board[location[0]][j] = '.';
								break;
							}
							if (board[location[0]][j] == '#') break;
						}
					}
					if (tank == '^') {
						for (int j = location[0] - 1; j >= 0; j--) {
							if (board[j][location[1]] == '*') {
								board[j][location[1]] = '.';
								break;
							}
							if (board[j][location[1]] == '#') break;
						}
					}
					if (tank == 'v') {
						for (int j = location[0] + 1; j < H; j++) {
							if (board[j][location[1]] == '*') {
								board[j][location[1]] = '.';
								break;
							}
							if (board[j][location[1]] == '#') break;
						}
					}
				}
				if (command[i].equals("U")) {
					tank = '^';
					board[location[0]][location[1]] = tank;
					if (location[0] - 1 >= 0 && board[location[0] - 1][location[1]] == '.') {
						board[location[0]][location[1]] = '.';
						board[location[0] - 1][location[1]] = '^';
						location[0] -= 1;
					}
				}
				if (command[i].equals("D")) {
					tank = 'v';
					board[location[0]][location[1]] = tank;
					if (location[0] + 1 < H && board[location[0] + 1][location[1]] == '.') {
						board[location[0]][location[1]] = '.';
						board[location[0] + 1][location[1]] = 'v';
						location[0] += 1;
					}
				}
				if (command[i].equals("R")) {
					tank = '>';
					board[location[0]][location[1]] = tank;
					if (location[1] + 1 < W && board[location[0]][location[1] + 1] == '.') {
						board[location[0]][location[1]] = '.';
						board[location[0]][location[1] + 1] = '>';
						location[1] += 1;
					}
				}
				if (command[i].equals("L")) {
					tank = '<';
					board[location[0]][location[1]] = tank;
					if (location[1] - 1 >= 0 && board[location[0]][location[1] - 1] == '.') {
						board[location[0]][location[1]] = '.';
						board[location[0]][location[1] - 1] = '<';
						location[1] -= 1;
					}
				}
				
				// for (char[] row : board) System.out.println(Arrays.toString(row));
			}
			System.out.print(String.format("#%d ", tc));
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
		}
		
	}

}
