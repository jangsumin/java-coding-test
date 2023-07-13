// 치즈 도둑(D4)
package swExpertAcademy;

import java.io.*;
import java.util.*;
 
public class Main_7733 {
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            // 맛의 정도의 최대값을 구하면 최대값 이상으로 탐색할 필요 없음.
            int max_day = 0;
            int max_result = 0;
             
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > max_day) max_day = map[i][j];
                }
            }
             
            for (int i = 0; i <= max_day; i++) {
                int result = 0;
                visited = new boolean[N][N];
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if (map[y][x] <= i) visited[y][x] = true; 
                    }
                }
                 
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if (visited[y][x] == false) {
                            dfs(y, x);
                            result += 1;
                        }
                    }
                }
                if (result > max_result) {
                    max_result = result;
                }       
            }
            System.out.println(String.format("%s%d %d", "#", tc, max_result));
        } 
    }
     
    static void dfs(int y, int x) {
        visited[y][x] = true;
         
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i]; 
            int nx = x + dx[i]; 
            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (visited[ny][nx] == false) {
                    dfs(ny, nx);
                }
            }
        }
        return;
    }
}