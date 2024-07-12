import java.io.*;
import java.util.*;

public class Main_4485 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = 1;
        StringTokenizer st;

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break; // 입력이 0이면 종료

            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            boolean[][] v = new boolean[N][N];

            dist[0][0] = cave[0][0];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            pq.offer(new int[] {0, 0, dist[0][0]});

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int x = curr[0];
                int y = curr[1];
                int cost = curr[2];

                if (v[x][y]) continue;
                v[x][y] = true;

                if (x == N - 1 && y == N - 1) break;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (dist[nx][ny] > cost + cave[nx][ny]) {
                        dist[nx][ny] = cost + cave[nx][ny];
                        pq.offer(new int[] {nx, ny, cost + cave[nx][ny]});
                    }
                }
            }

            System.out.println("Problem " + tc + ": " + dist[N - 1][N - 1]);
            tc++;
        }
    }
}
