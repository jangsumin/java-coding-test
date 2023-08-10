// bfs 활용해서 풀기

import java.io.*;
import java.util.*;

class Solution_게임맵최단거리 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static boolean[][] v;
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        map = maps;
        v = new boolean[N][M];
        
        bfs(0, 0);
        if (v[N - 1][M - 1]) return map[N - 1][M - 1];
        else return -1;
    }
    
    static void bfs(int x, int y) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        v[x][y] = true;
        q.offer(new int[] {x, y});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int xx = poll[0];
            int yy = poll[1];
            for (int i = 0; i < 4; i++) {
                int nx = xx + dx[i];
                int ny = yy + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && map[nx][ny] == 1) {
                    map[nx][ny] = map[xx][yy] + 1;
                    v[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }
}
