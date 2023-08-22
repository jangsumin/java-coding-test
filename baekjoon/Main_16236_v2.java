package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16236_v2 {
	static int N;
	static int[][] board;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int now_size = 2; // 아기 상어 크기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // board의 가로 세로 크기
		board = new int[N][N];
		
		int now_x = 0, now_y = 0; // 아기 상어 위치
		
		// board의 원소에 값을 할당
		// 아기 상어의 위치를 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					now_x = i;
					now_y = j;
					board[i][j] = 0;
				}
			}
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row)); 
		
		/*
		[4, 3, 2, 1]
		[0, 0, 0, 0]
		[0, 0, 0, 0]
		[1, 2, 3, 4]
		 */
		
		// for (int[] row : bfs(now_x, now_y)) System.out.println(Arrays.toString(row));
		
		/*
		[-1, -1,  2,  3]
		[3 ,  2,  1,  2]
		[2 ,  1,  0,  1]
		[3 ,  2, -1, -1]
		*/
		
		int eat_cnt = 0;
		int answer = 0; // 아기 상어가 모든 물고기를 먹기 위해 이동하는 거리이자 걸리는 시간
		
		while (true) {
			int[][] distance = bfs(now_x, now_y);
			int[] find_result = find_fish(distance);
			if (find_result.length == 1) { // 더 이상 먹을 물고기를 찾을 수 없음
				break;
			} else {
				now_x = find_result[0]; // 아기 상어 위치 변경
				now_y = find_result[1];
				answer += find_result[2]; // 물고기까지의 거리만큼 정답 증가
				board[now_x][now_y] = 0;
				eat_cnt += 1;
				if (eat_cnt == now_size) {
					now_size += 1;
					eat_cnt = 0;
				}
			}
		}
		
		System.out.println(answer); // 정답 출력
	}
	
	static int[][] bfs(int x, int y) { // 아기 상어 위치에서 다른 물고기까지의 최단 거리를 저장하는 배열을 반환 
		ArrayDeque<int[]> q = new ArrayDeque<>();
		int[][] distance = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distance[i][j] = -1;
			}
		}
		distance[x][y] = 0;
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int poll_x = temp[0];
			int poll_y = temp[1];
			for (int i = 0; i < 4; i++) {
				int nx = poll_x + dx[i];
				int ny = poll_y + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) { // board 범위를 벗어날 수 없음
					// 방문하지 않은 지점이어야 하고, 아기 상어의 크기보다 같거나 작아야 아기 상어가 이동 가능
					if (distance[nx][ny] == -1 && board[nx][ny] <= now_size) {
						distance[nx][ny] = distance[poll_x][poll_y] + 1;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
		return distance;
	}
	
	static int[] find_fish(int[][] distance) {
		int x = 0;
		int y = 0;
		int min_distance = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 물고기가 있어야 하는 지점이므로, 1보다 같거나 커야 하고, 아기 상어의 크기보다 작아야 함
				if (distance[i][j] != -1 && board[i][j] >= 1 && board[i][j] < now_size) {
					if (distance[i][j] < min_distance) {
						x = i;
						y = j;
						min_distance = distance[i][j];
					}
				}
			}
		}
		
		if (min_distance == Integer.MAX_VALUE) { // 최소 거리가 재할당되지 않았다면 먹을 수 있는 고기가 없다는 뜻
			return new int[] {-1};
		} else { // 먹을 수 있는 고기가 있다는 뜻
			return new int[] {x, y, min_distance};
		}
	}

}
