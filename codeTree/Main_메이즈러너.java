package codeTree;

// 중요한 사항
// 회전할 때, 내구도 1씩 감소
// 그러다가 내구도가 0이 되면 빈 칸으로 변경

import java.io.*;
import java.util.*;

public class Main_메이즈러너 {
    static int N, M, K;
    static int[][] board;
    static ArrayList<int[]> people;
    static int exitX, exitY;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // for (int[] row : board) System.out.println(Arrays.toString(row));

        people = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // x좌표, y좌표, 이동 거리, 이동 가능 flag
            people.add(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0});
        }
        // for (int[] el : people) System.out.println(Arrays.toString(el));

        st = new StringTokenizer(br.readLine());
        exitX = Integer.parseInt(st.nextToken()) - 1;
        exitY = Integer.parseInt(st.nextToken()) - 1;
        // System.out.println(exitX + " " + exitY);

        while (K-- > 0) {
            move();

            boolean isAllExit = true;
            for (int p = 0; p < people.size(); p++) {
                if (!(people.get(p)[0] == exitX && people.get(p)[1] == exitY)) {
                    isAllExit = false;
                    break;
                }
            }

            if (isAllExit) break;

            int[] points = findSquare();
            turn(points);

            // for (int[] row : board) System.out.println(Arrays.toString(row));
            // System.out.println("출구 좌표: " + exitX + " " + exitY);
            // for (int[] el : people) System.out.println(Arrays.toString(el));
        }

        int answer = 0;
        for (int p = 0; p < people.size(); p++) {
            answer += people.get(p)[2];
        }

        System.out.println(answer);
        System.out.println((exitX + 1) + " " + (exitY + 1));
        br.close();
    }

    // 모든 사람들이 이동하는지 확인하기
    // 이동한다면 이동거리 누적
    static void move() {
        for (int p = 0; p < people.size(); p++) {
            int x = people.get(p)[0];
            int y = people.get(p)[1];
            int cnt = people.get(p)[2];

            if (x == exitX && y == exitY) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == 0 && isCloser(x, y, nx, ny)) {
                    people.set(p, new int[] {nx, ny, cnt + 1});
                    break;
                }
            }
        }

        // for (int[] person : people) System.out.println(Arrays.toString(person));
    }

    // 출구와 더 가까워지는지 확인하기
    static boolean isCloser(int prevX, int prevY, int nextX, int nextY) {
        if ((Math.abs(prevX - exitX) + Math.abs(prevY - exitY)) > (Math.abs(nextX - exitX) + Math.abs(nextY - exitY))) return true;
        return false;
    }

    // 최소 참가자 한 명을 포함하는 가장 작은 정사각형 구하기
    static int[] findSquare() {
        for (int s = 2; s <= N; s++) {
            for (int i = 0; i < N - s + 1; i++) {
                for (int j = 0; j < N - s + 1; j++) {
                    boolean isContainPerson = false;
                    boolean isContainExit = false;

                    for (int x = 0; x < s; x++) {
                        for (int y = 0; y < s; y++) {
                            if (i + x == exitX && j + y == exitY) isContainExit = true;
                            if (!isContainPerson) {
                                for (int p = 0; p < people.size(); p++) {
                                    if (i + x == people.get(p)[0] && j + y == people.get(p)[1] && !(i + x == exitX && j + y == exitY)) {
                                        isContainPerson = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    // x 시작 좌표, x 종료 좌표, y 시작 좌표, y 종료 좌표
                    if (isContainExit && isContainPerson) return new int[] {i, i + s - 1, j, j + s - 1};
                }
            }
        }

        return new int[] {-1, -1, -1, -1};
    }

    // 시계 방향으로 90도 회전
    // 내구도 1씩 감소
    static void turn(int[] square) {
        int sx = square[0];
        int ex = square[1];
        int sy = square[2];
        int ey = square[3];

        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                if (board[i][j] > 0) board[i][j]--;
            }
        }

        // 부분 회전
        int n = ex - sx + 1;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = board[sx + n - 1 - j][sy + i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[sx + i][sy + j] = temp[i][j];
            }
        }

        // 출구 좌표 회전
        int[] tempExit = {exitX - sx, exitY - sy}; // 0, 0으로 옮기기
        exitX = tempExit[1] + sx;
        exitY = n - tempExit[0] - 1 + sy;

        // 참가자 좌표 회전
        for (int p = 0; p < people.size(); p++) {
            int x = people.get(p)[0];
            int y = people.get(p)[1];
            int cnt = people.get(p)[2];

            if(sx <= x && x < sx + n && sy <= y && y < sy + n) {
                int ox = x - sx, oy = y - sy;
                int rx = oy, ry = n - ox - 1;
                people.set(p, new int[] {rx + sx, ry + sy, cnt});
            }
        }
    }
}
