package codeTree;

import java.io.*;
import java.util.*;

public class Main_예술성 {
    static int n;
    static int[][] board;
    static int[][] nextBoard;
    static int result = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // for (int[] row : board) System.out.println(Arrays.toString(row));

        int turn = 0;
        while (turn++ < 4) {
            result += harmony(); // 조화 점수 구하기
            nextBoard = new int[n][n];
            rotation1(); // 십자 모양 반시계 방향 90도 회전
            rotation2(); // 각 4개의 정사각형 개별적으로 시계방향으로 90도 회전
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = nextBoard[i][j];
                }
            }
        }

        System.out.println(result);
    }

    static int harmony() {
        int[][] v = new int[n][n];
        List<Integer> cnt_list = new ArrayList<>();
        List<Integer> num_list = new ArrayList<>();
        
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j] == 0) { // 방문하지 않은 좌표여야 함
                    num_list.add(board[i][j]); // 해당 좌표 번호를 num_list에 저장 -> 그룹이 하나 생성
                    int cnt = 1;
                    v[i][j] = idx; // 고유번호 적기
                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {i, j});
                    while (!q.isEmpty()) {
                        int[] q_poll = q.poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = q_poll[0] + dx[dir];
                            int ny = q_poll[1] + dy[dir];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && v[nx][ny] == 0 && board[nx][ny] == board[q_poll[0]][q_poll[1]]) {
                                v[nx][ny] = v[q_poll[0]][q_poll[1]];
                                q.offer(new int[] {nx, ny});
                                cnt += 1;
                            }
                        }
                    }
                    cnt_list.add(cnt);
                    idx++;
                }
            }

        }

        // for (int[] row : v) System.out.println(Arrays.toString(row));
        // System.out.println(num_list.toString());
        // System.out.println(cnt_list.toString());

        int score = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && v[i][j] != v[nx][ny]) {
                        int g1 = v[i][j] - 1;
                        int g2 = v[nx][ny] - 1;
                        int num1 = num_list.get(g1);
                        int num2 = num_list.get(g2);
                        int cnt1 = cnt_list.get(g1);
                        int cnt2 = cnt_list.get(g2);

                        score += (cnt1 + cnt2) * num1 * num2;
                    }
                }
            }
        }

        return score / 2;
    }

    static void rotation1() {
        int[] mid_row = new int[n];
        int[] mid_col = new int[n];

        for (int i = 0; i < n; i++) {
            mid_row[i] = board[n / 2][i];
            mid_col[i] = board[i][n / 2];
        }

        for (int i = 0; i < n; i++) {
            nextBoard[n / 2][i] = mid_col[i];
            nextBoard[i][n / 2] = mid_row[n - i - 1];
        }

        // for (int[] row : board) System.out.println(Arrays.toString(row));
    }

    static void rotateSquare(int sx, int sy, int n) {
        for (int i = sx; i < sx + n; i++) {
            for (int j = sy; j < sy + n; j++) {
                int ox = i - sx;
                int oy = j - sy;

                int rx = oy;
                int ry = n - ox - 1;
                nextBoard[rx + sx][ry + sy] = board[i][j];
            }
        }
    }

    static void rotation2() {
        // 시작점은 4곳으로 각각 (0, 0), (0, n / 2 + 1), (n / 2 + 1, 0), (n / 2 + 1, n / 2 + 1)
        rotateSquare(0, 0, n / 2);
        rotateSquare(0, n / 2 + 1, n / 2);
        rotateSquare(n / 2 + 1, 0, n / 2);
        rotateSquare(n / 2 + 1, n / 2 + 1, n / 2);

        // for (int[] row : nextBoard) System.out.println(Arrays.toString(row));
    }
}
