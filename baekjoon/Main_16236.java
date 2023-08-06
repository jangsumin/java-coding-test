package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16236 {
	static int[][] board;
	static final int[] dx = {0, -1, 1, 0};
	static final int[] dy = {-1, 0, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N 초기화
		int N = Integer.parseInt(br.readLine());
		// board, distance, visited 초기화
		board = new int[N][N];
		
		int size = 2; // 현재 아기 상어 사이즈
		int eat = 0; // 먹은 물고기 수
		int move = 0; // 움직인 거리
		
		// 아기 상어 위치
		int[] curr = new int[2];
		// board 입력, 아기 상어 위치 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					curr[0] = i;
					curr[1] = j;
					board[i][j] = 0;
				}
			}
		}
		
		// for (int[] a : board) System.out.println(Arrays.toString(a));
		
		while (true) {
			// 우선순위 큐를 사용해서 거리 순 오름차순, y좌표 오름차순, x좌표 오름차순으로 큐 내부 정렬
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) ->
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );
            boolean[][] visited = new boolean[N][N];

            q.offer(new int[]{curr[0], curr[1], 0}); // y좌표, x좌표, 이동한 거리
            visited[curr[0]][curr[1]] = true;

            boolean check = false; // 상어가 먹이를 먹었는지 체크할 변수

            while (!q.isEmpty()) {
                curr = q.poll();

                if (board[curr[0]][curr[1]] != 0 && board[curr[0]][curr[1]] < size) { // 먹이가 있으면서 상어의 사이즈보다 작다면?
                    board[curr[0]][curr[1]] = 0; // 물고기를 제거
                    eat++; 
                    move += curr[2]; // 움직인 거리를 총 움직인 거리에 추가
                    check = true; // 먹이 먹었다고 체크
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int ny = curr[0] + dy[k];
                    int nx = curr[1] + dx[k];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visited[ny][nx] || board[ny][nx] > size)
                        continue;

                    q.offer(new int[]{ny, nx, curr[2] + 1});
                    visited[ny][nx] = true;
                }
            }

            if (!check) // 큐가 비워질 때까지 먹이를 먹은적이 없다면, 더 이상 먹은 물고기가 없으므로 탈출
                break;

            if (size == eat) { // 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기를 증가
                size++;
                eat = 0;
            }
        }

        System.out.println(move);
		
	}

}