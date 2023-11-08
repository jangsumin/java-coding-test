package baekjoon;

import java.io.*;
import java.util.*;

class Node {
	int x, y, cost, key;
	public Node(int x, int y, int cost, int key) {
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.key = key;
	}
}

public class Main_1194 {
	static int N, M;
	static char[][] map;
	static Node start;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int startX = 0, startY = 0; // 시작 위치
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String rowInput = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = rowInput.charAt(j);
				if (map[i][j] == '0') start = new Node(i, j, 0, 0);
			}
		}
		
		// for (char[] row : map) System.out.println(Arrays.toString(row));
		
		System.out.println(bfs());	
	}
	
	static int bfs() {
		ArrayDeque<Node> q = new ArrayDeque<>();
		boolean[][][] v = new boolean[N][M][64];
		q.offer(start);
		v[start.x][start.y][0] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if (map[cur.x][cur.y] == '1') return cur.cost; // 달을 만나서 종료
			 
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (v[nx][ny][cur.key] || map[nx][ny] == '#') continue;
				
				if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
					int nextKey = 1 << (map[nx][ny] - 'a');
					nextKey = cur.key | nextKey;
					v[nx][ny][nextKey] = true;
					q.offer(new Node(nx, ny, cur.cost + 1, nextKey));
				}
				else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
					if ((cur.key & 1 << (map[nx][ny] - 'A')) == (int)Math.pow(2, map[nx][ny] - 'A')) {
						v[nx][ny][cur.key] = true;
						q.offer(new Node(nx, ny, cur.cost + 1, cur.key));
					}
				}
				else {
					v[nx][ny][cur.key] = true;
					q.offer(new Node(nx, ny, cur.cost + 1, cur.key));
				}
			}
		}
		return -1;
	}
}
