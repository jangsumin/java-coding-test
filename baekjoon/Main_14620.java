package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14620 {
    static int n;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(result);

    }

    static void dfs(int count, int sum) {
        if (count == 3) {
            result = Math.min(result, sum);
        } else {
            for (int i=2; i<n; i++) {
                for (int j=2; j<n; j++) {
                    if (!visited[i][j] && check(i,j)) {
                        visited[i][j] = true;
                        int hap = sum(i,j);
                        dfs(count+1, sum + hap);
                        visitedClear(i,j);
                        visited[i][j] = false;
                    }
                }
            }
        }
    }

    static boolean check(int x, int y) {
        for (int i=0; i<dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (visited[nx][ny]) {
                return false;
            }
        }
        return true;
    }

    static void visitedClear(int x, int y) {
        for (int i=0; i<dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[nx][ny] = false;
        }
    }

    static int sum(int x, int y) {
        int sum1 = map[x][y];
        for (int i=0; i<dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[nx][ny] = true;
            sum1 += map[nx][ny];
        }
        return sum1;
    }
}