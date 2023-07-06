// 파핑파핑 지뢰찾기
package swExpertAcademy;

import java.io.*;
 
public class Main_Pre_Training7 {
 
    static int n, res, bombCnt[][];
    static char map[][];
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i = 1; i <= tc; i++) {
            n = Integer.parseInt(br.readLine());
            res = 0;
            map = new char[n][n];
            bombCnt = new int[n][n];
            for (int j = 0; j < n; j++) {
                map[j] = br.readLine().toCharArray();
            }
 
            count();
 
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // 주변에 지뢰가 없고 현 위치가 지뢰가 아니라면
                    if(bombCnt[j][k] == 0 && map[j][k] == '.') {
                        click(j, k);
                        res++;
                    }
                }
            }
            
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(bombCnt[j][k] > 0 && map[j][k] != '*') {
                        res++;
                    }
                }
            }
            System.out.println("#" + i + " " + res);
        }
    }
 
    private static void click(int x, int y) {
        int now = bombCnt[x][y]; 
        bombCnt[x][y] = -1;
        if(now == 0) {
            for (int i = 0; i < 8; i++) {
                if(x + dx[i] < 0 || x + dx[i] >= n || y + dy[i] < 0 || y + dy[i] >= n
                        || bombCnt[x + dx[i]][y + dy[i]] == -1 || map[x + dx[i]][y + dy[i]] == '*') continue;
                click(x + dx[i], y + dy[i]);
            }
        }
    }
    
    static void count() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if(map[x][y] == '.') {
                    int cnt = 0;
                    for (int i = 0; i < 8; i++) {
                        if(x + dx[i] < 0 || x + dx[i] >= n || y + dy[i] < 0 || y + dy[i] >= n || map[x + dx[i]][y + dy[i]] != '*') continue;
                        cnt++;
                    }
                    bombCnt[x][y] = cnt;
                }
            }
        }
    }
}