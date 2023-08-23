package baekjoon;

import java.io.*;
import java.util.*;

public class Main_17472 {
	static int N, M;
	static int[][] board;
  static int island_cnt = 0;
	static boolean[][] v;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
  static PriorityQueue<int[]> pq;
  static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬의 개수를 구하기
    island_cnt = 2;
		v = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 1 && !v[i][j]) {
					bfs(i, j, island_cnt);
					island_cnt++;
				}
			}
		}
		
		// for (int[] row : board) System.out.println(Arrays.toString(row));
    // System.out.println();
		// System.out.println(island_cnt);
		;
    pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] != 0) bridge_info(i, j, board[i][j]);
      }
    }

    // for (int[] el : pq) System.out.println(Arrays.toString(el));
		
    island_cnt--;
		parents = new int[island_cnt];
    for (int i = 1; i < island_cnt; i++) {
      parents[i] = i;
    }
    int answer = shortest_distance();
    System.out.println(answer == 0 ? -1 : answer);
	}

  static int shortest_distance() {
    int sum = 0;
    int size = pq.size();
    for (int i = 0; i < size; i++) {
      int[] info = pq.poll();
      // System.out.println(Arrays.toString(info));
      int x = info[0];
      int y = info[1];

      if (find(x) != find(y)) {
        sum += info[2];
        union(x, y);
      }
    }

    int rx = parents[1];
    for (int i = 2; i < island_cnt; i++) {
      if (rx != find(parents[i])) return 0;
    }

    return sum;
  }

  static int find(int x) {
    if(parents[x] == x) return x;
    int rx = find(parents[x]);
    return rx;
  }

  static void union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x < y) {
      parents[y] = x;
    } else {
      parents[x] = y;
    }
  }

  static void bridge_info(int x, int y, int idx) {
    ArrayDeque<int[]> q = new ArrayDeque<>();
    v = new boolean[N][M];
    for (int d = 0; d < 4; d++) {
      q.offer(new int[] {x, y, 0});
      v[x][y] = true;

      while (!q.isEmpty()) {
        int[] q_poll = q.poll();
        int px = q_poll[0];
        int py = q_poll[1];
        int move = q_poll[2];

        int nx = px + dx[d];
        int ny = py + dy[d];

        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny]) {
          if (board[nx][ny] != idx) {
            if (board[nx][ny] == 0) {
              v[nx][ny] = true;
              q.offer(new int[] {nx, ny, move + 1});
            } else {
              if (move > 1) {
                pq.offer(new int[] {idx - 1, board[nx][ny] - 1, move});
                break;
              }
            }
          }
        }
      }
    }

  }
	
	static void bfs(int x, int y, int number) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[x][y] = true;
		board[x][y] = number;
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] q_poll = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = q_poll[0] + dx[i];
				int ny = q_poll[1] + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && board[nx][ny] == 1) {
					board[nx][ny] = number;
					v[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

}