package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17144 {
	static int R, C;
	static int[][] board;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int[] robot_location_x = new int[2];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		// 공기청정기 위치를 담기 위한 인덱스
		int idx = 0;
		// board 입력
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) robot_location_x[idx++] = i;
			}
		}
		
		for (int i = 0; i < T; i++) {
			// 확산
			spread();
			
			// 공기청정기 작동
			work();			
		}

		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] != -1) result += board[i][j];
			}
		}
		
		System.out.println(result);
	}
	
	static void spread() {
		// 확산되는 미세먼지를 원본 board에 더하기 위한 임시 2차원 배열
		int[][] temp = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				// board가 5 미만이면 5로 나누어도 각 방향으로 확산되는 미세먼지는 0이다.
				if (board[i][j] >= 5) {
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx >= 0 && nx < R && ny >= 0 && ny < C && board[nx][ny] != -1) {
							temp[nx][ny] += board[i][j] / 5;
							temp[i][j] -= board[i][j] / 5;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				board[i][j] += temp[i][j];
			}
		}
	}
	
	static void work() {
		ArrayDeque<Integer> up_q = new ArrayDeque<>();
		
		for (int i = 1; i < C - 1; i++) up_q.offer(board[robot_location_x[0]][i]);
		for (int i = robot_location_x[0]; i > 0; i--) up_q.offer(board[i][C - 1]);
		for (int i = C - 1; i > 0; i--) up_q.offer(board[0][i]);
		for (int i = 0; i < robot_location_x[0]; i++) up_q.offer(board[i][0]);
		
		up_q.pollLast();
		up_q.offerFirst(0);
		
		for (int i = 1; i < C - 1; i++) board[robot_location_x[0]][i] = up_q.pollFirst();
		for (int i = robot_location_x[0]; i > 0; i--) board[i][C - 1] = up_q.pollFirst();
		for (int i = C - 1; i > 0; i--) board[0][i] = up_q.pollFirst();;
		for (int i = 0; i < robot_location_x[0]; i++) board[i][0] = up_q.pollFirst();
		
		ArrayDeque<Integer> down_q = new ArrayDeque<>();
		
		for (int i = 1; i < C - 1; i++) down_q.offer(board[robot_location_x[1]][i]);
		for (int i = robot_location_x[1]; i < R - 1; i++) down_q.offer(board[i][C - 1]);
		for (int i = C - 1; i > 0; i--) down_q.offer(board[R - 1][i]);
		for (int i = R - 1; i > robot_location_x[1]; i--) down_q.offer(board[i][0]);
		
		down_q.pollLast();
		down_q.offerFirst(0);
		
		for (int i = 1; i < C - 1; i++) board[robot_location_x[1]][i] = down_q.pollFirst();
		for (int i = robot_location_x[1]; i < R - 1; i++) board[i][C - 1] = down_q.pollFirst();
		for (int i = C - 1; i > 0; i--) board[R - 1][i] = down_q.pollFirst();
		for (int i = R - 1; i > robot_location_x[1]; i--) board[i][0] = down_q.pollFirst();
	}

}
