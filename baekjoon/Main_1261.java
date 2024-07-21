import java.io.*;
import java.util.*;

public class Main_1261 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[M][N];
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        // for (int[] row : board) System.out.println(Arrays.toString(row));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {0, 0, 0}); // 2번 인덱스가 가중치(부순 벽의 갯수)

        boolean[][] v = new boolean[M][N];
        v[0][0] = true;
        int minCost = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cx = curr[0], cy = curr[1];
            int cost = curr[2];

            if (cx == M - 1 && cy == N - 1) {
                minCost = cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N || v[nx][ny]) continue;

                v[nx][ny] = true;
                if (board[nx][ny] == 0) pq.offer(new int[] {nx, ny, cost});
                else pq.offer(new int[] {nx, ny, cost + 1});
            }
        }

        System.out.println(minCost);
    }
}
