package codeTree;

import java.io.*;
import java.util.*;

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main_코드트리빵 {
    static int N, M;
    static int[][] board;
    static int[][] stores;
    static Map<Integer, Pair> people = new HashMap<>();
    static int[] dx = {-1, 0, 0, 1}; // 상, 좌, 우, 하
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // for (int[] row : board) System.out.println(Arrays.toString(row));

        // 편의점 입력 받기
        stores = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cx = Integer.parseInt(st.nextToken()) - 1;
            int cy = Integer.parseInt(st.nextToken()) - 1;
            stores[i][0] = cx;
            stores[i][1] = cy;
            board[cx][cy] = -2; // -1은 접근 불가 상태
        }
        // for (int[] row : stores) System.out.println(Arrays.toString(row));
        // for (int[] row : board) System.out.println(Arrays.toString(row));
        
        int t = 1;
        while (true) {
            // 격자에 있는 사람들 모두가 본인이 가고 싶은 편의점 방향으로 1칸 이동
            // 상좌우하의 우선 순위로 이동
            // 모든 사람들이 이동하고 나서, 편의점에 이동한 사람들을 찾아 편의점에 접근할 수 없게 만들기
            movePeople();

            // // 자신의 목적지인 편의점에서 가장 가까운 베이스캠프 찾기
            if (t <= M) {
                int[] camp = findNearCamp(t);
                // 베이스캠프로 사람 배치
                people.put(t, new Pair(camp[0], camp[1]));
                board[camp[0]][camp[1]] = -1; // 더 이상 접근 불가능한 베이스캠프가 됨
            }

            // for (int[] row : board) System.out.println(Arrays.toString(row));
            
            if (t >= M) {
                boolean isEnd = true;
                for (int i = 1; i < people.size() + 1; i++) {
                    if (!(people.get(i).x == stores[i - 1][0] && people.get(i).y == stores[i - 1][1])) {
                        isEnd = false;
                        break;
                    }
                }

                if (isEnd) break;
            }
            t++;
        }  
        System.out.println(t);
        br.close();
    }

    static void movePeople() {
        for (int i = 1; i < people.size() + 1; i++) {
            // 이미 도달한 경우 건너뛰기
            if (people.get(i).x == stores[i - 1][0] && people.get(i).y == stores[i - 1][1]) continue;
            
            int targetX = stores[i - 1][0];
            int targetY = stores[i - 1][1];

            boolean[][] visited = new boolean[N][N];
            int[][] step = new int[N][N];
            ArrayDeque<int[]> q = new ArrayDeque<>();
            visited[targetX][targetY] = true;
            q.offer(new int[] {targetX, targetY});
            step[targetX][targetY] = 0;

            while (!q.isEmpty()) {
                int[] pollQ = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = pollQ[0] + dx[d];
                    int ny = pollQ[1] + dy[d];
                    if (isInRange(nx, ny) && !visited[nx][ny] && board[nx][ny] != -1) {
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                        step[nx][ny] = step[pollQ[0]][pollQ[1]] + 1;
                    }
                }
            }

            // for (int[] row : step) System.out.println(Arrays.toString(row));

            int px = people.get(i).x, py = people.get(i).y;
            int minD = Integer.MAX_VALUE;
            int nextX = -1;
            int nextY = -1;
            for (int d = 0; d < 4; d++) {
                int nx = px + dx[d];
                int ny = py + dy[d];
                if (isInRange(nx, ny) && board[nx][ny] != -1 && visited[nx][ny] && step[nx][ny] < minD) {
                    minD = step[nx][ny];
                    nextX = nx;
                    nextY = ny;
                }
            }

            people.put(i, new Pair(nextX, nextY));
        }

        // 만약 편의점에 도착하면 해당 편의점은 모두 지나갈 수 없게 만들기
        for (int i = 1; i < people.size() + 1; i++) {
            if (people.get(i).x == stores[i - 1][0] && people.get(i).y == stores[i - 1][1]) {
                board[stores[i - 1][0]][stores[i - 1][1]] = -1;
            }
        }
    }

    // 본인 목적지의 편의점에서 가장 가까운 베이스 캠프 찾기
    static int[] findNearCamp(int num) {
        int targetX = stores[num - 1][0];
        int targetY = stores[num - 1][1];

        int campX = -1;
        int campY = -1;
        int minD = Integer.MAX_VALUE;

        // bfs로 가장 가까운 베이스 캠프 찾기
        boolean[][] visited = new boolean[N][N];
        int[][] step = new int[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[targetX][targetY] = true;
        q.offer(new int[] {targetX, targetY});
        step[targetX][targetY] = 0;

        while (!q.isEmpty()) {
            int[] pollQ = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pollQ[0] + dx[i];
                int ny = pollQ[1] + dy[i];
                if (isInRange(nx, ny) && !visited[nx][ny] && board[nx][ny] != -1) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                    step[nx][ny] = step[pollQ[0]][pollQ[1]] + 1;
                }
            }
        }
        
        // for (int[] row : step) System.out.println(Arrays.toString(row));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (step[i][j] < minD && board[i][j] == 1 && visited[i][j]) {
                    minD = step[i][j];
                    campX = i;
                    campY = j;
                }
            }
        }
    
        return new int[] {campX, campY};
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
