import java.io.*;
import java.util.*;

public class Main_2583 {
    static int M, N;
    static int[][] board;
    static boolean[][] v;
    static int areaCount = 0;
    static List<Integer> list = new ArrayList<>();
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[M][N];
        v = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    board[x][y] = -1;
                }
            }
        }

        // for (int[] row : board) System.out.println(Arrays.toString(row));

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 && !v[i][j]) {
                    list.add(dfs(i, j));
                    areaCount++;
                }
            }
        }

        System.out.println(areaCount);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) System.out.print(list.get(i));
            else System.out.print(list.get(i) + " ");
        }
    }

    static int dfs(int x, int y) {
        int totalCount = 1;
        v[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || v[nx][ny] || board[nx][ny] == -1) continue;

            totalCount += dfs(nx, ny);
        }

        return totalCount;
    }
}
