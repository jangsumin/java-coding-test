package codeTree;

import java.io.*;
import java.util.*;

public class Main_나무박멸 {
    static int n, m, k, c;
    static int[][] board;
    static int[][] remain; // 제초제가 남아있는 기간
    static int delete_cnt = 0; // 박멸한 나무 수
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] dx2 = {-1, -1, 1, 1}; // 좌상, 우상, 우하, 좌하
    static int[] dy2 = {-1, 1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        remain = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 보드 확인 코드
        // for (int[] row : board) System.out.println(Arrays.toString(row));

        while (m-- > 0) { // m년간 반복
            grow(); // 성장
            create(); // 번식
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (remain[i][j] > 0) remain[i][j]--;
                }
            }
            delete(); // 박멸
        }

        System.out.println(delete_cnt);
    }

    static void grow() {
        int[][] save = new int[n][n]; // 각 나무마다 성장 정도 저장
        boolean[][] v = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    // 인접한 네 개의 칸에 나무가 있는지 확인
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] > 0) cnt++;
                    }
                    save[i][j] = cnt;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] += save[i][j];
            }
        }
    }

    static void create() {
        int[][] save = new int[n][n]; // 번식 나무 수 저장
        boolean[][] v = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) { // 주변에 0인 공간에 번식 진행
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0 && remain[nx][ny] == 0) cnt++;
                    }

                    if (cnt > 0) {
                        int trees = board[i][j] / cnt;
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0 && remain[nx][ny] == 0) save[nx][ny] += trees;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] += save[i][j];
            }
        }
    }

    static void delete() {
        int[][] save = new int[n][n];
        int max_cnt = 0;
        int sx = 0, sy = 0; // 박멸할 나무 위치
        boolean[][] v = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    int cnt = board[i][j]; // 중심 나무 수 포함
                
                    for (int d = 0; d < 4; d++) { // 주변에 0인 공간에 번식 진행
                        int nx, ny;
                        for (int r = 1; r <= k; r++) {
                            nx = i + dx2[d] * r;
                            ny = j + dy2[d] * r;
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                                if (board[nx][ny] == 0 || board[nx][ny] == -1) break;
                                cnt += board[nx][ny];
                            }
                        }
                    }

                    save[i][j] = cnt;
                    max_cnt = Math.max(max_cnt, cnt);
                }
            }
        }

        next: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (save[i][j] == max_cnt) {
                    sx = i;
                    sy = j;
                    break next;
                } 
            }
        }

        board[sx][sy] = 0;
        remain[sx][sy] = c;
        for (int d = 0; d < 4; d++) { // 주변에 0인 공간에 번식 진행
            int nx, ny;
            for (int r = 1; r <= k; r++) {
                nx = sx + dx2[d] * r;
                ny = sy + dy2[d] * r;
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (board[nx][ny] == 0 || board[nx][ny] == -1) {
                        remain[nx][ny] = c;
                        break;
                    }
                    board[nx][ny] = 0;
                    remain[nx][ny] = c;
                }
            }
        }
    
        delete_cnt += max_cnt; // 박멸할 나무 수에 더하기
    }
}
