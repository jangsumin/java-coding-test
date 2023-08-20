package baekjoon;

// import java.io.*;
// import java.util.*;

// public class Main_17135 {
//   static int N, M, D;
//   static int[][] board;
//   static int[] save = new int[3];
//   static int enemy = 0;
//   public static void main(String[] args) throws Exception {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//     StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//     N = Integer.parseInt(st.nextToken());
//     M = Integer.parseInt(st.nextToken());
//     D = Integer.parseInt(st.nextToken());
//     board = new int[N][M];

//     // 적의 위치 보드에 입력
//     for (int i = 0; i < N; i++) {
//       st = new StringTokenizer(br.readLine(), " ");
//       for (int j = 0; j < M; j++) {
//         board[i][j] = Integer.parseInt(st.nextToken());
//       }
//     }

//     // for (int[] row : board) System.out.println(Arrays.toString(row));

//     comb(0, 0);
//     System.out.println(enemy);
//   }

//   // 궁수의 위치 조합
//   static void comb(int cnt, int start) {
//     // 궁수의 수는 3
//     if (cnt == 3) {
//       // 매 조합마다 사용해야 하는 board는 같아야 하므로 복사
//       int[][] new_board = copy(board);
//       int enemy_cnt = 0;

//       for (int turn = 0; turn < N; turn++) {
//         List<int[]> list = new ArrayList<>();

//         // 궁수마다 한 턴에서 죽여야 하는 적의 위치를 list에 담기(중복 X)
//         for (int loc = 0; loc < 3; loc++) {
//           int min = Integer.MAX_VALUE;
//           int[] enemy_location = {-1, -1};


//           for (int i = N - 1; i >= 0; i--) {
//             for (int j = 0; j < M; j++) {
//               if (new_board[i][j] == 1) {
//                 int temp_dis = distance(new int[] {i, j}, new int[] {N, save[loc]});
//                 if (temp_dis <= D && temp_dis < min) {
//                   enemy_location[0] = i;
//                   enemy_location[1] = j;
//                   min = temp_dis;
//                 }
//               }
//             }
//           }

//           // 위 for문이 끝나고 나면 가장 가까우면서 왼쪽인 적의 위치를 알게 됨
//           if (enemy_location[0] != -1 && enemy_location[1] != -1) {
//             if (!check(enemy_location, list)) {
//               list.add(enemy_location);
//             }
//           }
//         }

//         for (int[] el : list) {
//           new_board[el[0]][el[1]] = 0;
//           enemy_cnt += 1;
//         }

//         for (int i = N - 1; i >= 1; i--) {
//           for (int j = 0; j < M; j++) {
//             new_board[i][j] = new_board[i - 1][j];
//           }
//         }

//         for (int i = 0; i < M; i++) {
//           new_board[0][i] = 0;
//         }

//         // System.out.println(turn + "번째");
//         // for (int[] el : list) System.out.println(Arrays.toString(el));
//         // for (int[] row : new_board) System.out.println(Arrays.toString(row));
//         // System.out.println();
//       }

//       enemy = Math.max(enemy_cnt, enemy);
//       return;
//     }

//     for (int i = start; i < M; i++) {
//       save[cnt] = i;
//       comb(cnt + 1, i + 1);
//     }
//   }

//   // 새 2차원 배열 생성
//   static int[][] copy(int[][] origin) {
//     int[][] new_arr = new int[N][M];

//     for (int i = 0; i < N; i++) {
//       for (int j = 0; j < M; j++) {
//         new_arr[i][j] = origin[i][j];
//       }
//     }

//     return new_arr;
//   }

//   // 궁수와 적 거리 계산
//   static int distance(int[] enemy, int[] arc) {
//     return Math.abs(enemy[0] - arc[0]) + Math.abs(enemy[1] - arc[1]);
//   }

//   static boolean check(int[] to_find, List<int[]> origin) {
//     for (int[] el : origin) {
//       if (el[0] == to_find[0] && el[1] == to_find[1]) return true;
//     }
//     return false;
//   }
// }

import java.io.*;
import java.util.*;

public class Main_17135 {
	//입력값
	static int n, m, d, answer = 0, map[][];
	//방문여부 체크, 배열 복원을 위한 배열
	static boolean visited[], save[][];
	//조합
	static void comb(int depth) {
		//궁수 3명 배치 완료
		if (depth == 3) {
			play();
			return;
		}
		for (int i = depth; i < m; i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static void play() {
		//각 궁수가 타겟을 저장
		int[][] target = new int[3][2];
		int cnt = 0;
		while (true) {
			int index = 0;
			boolean gameOver = true;
			//[n,i]에 위치한 궁수의 타겟 좌표를 저장
			for (int i = 0; i < m; i++) {
				if (visited[i]) target[index++] = getTarget(n, i);
			}
			//맵에서 타겟의 카운트를 +1 -> 맵에서 2이상의 값을 가진 적이 죽는 것
			for (int[] a : target) {
				if (a[0] != -1) map[a[0]][a[1]]++;
			}
			for (int i = 0; i < m; i++) {
				for (int j = n; j > 0; j--) {
					//없앤 타겟 수 +1한 후 맵에서 제외(=0)
					if (map[j-1][i] > 1) {
						cnt++;
						map[j-1][i] = 0;
					}
					//한 칸씩 아래로 당김
					int temp = map[j][i];
					map[j][i] = map[j-1][i];
					map[j-1][i] = temp;
					//맵에 존재하는 타겟이 없는 경우 게임 종료
					if (map[j][i] == 1) gameOver = false;
				}
				map[n][i] = 0;
			}
			if (gameOver) break;
		}		
		//최댓값 갱신
		answer = Math.max(answer, cnt);
		back();
	}
	//하에서 상으로 왼쪽부터 탐색하며 가까운 타겟 좌표를 리턴
	static int[] getTarget(int x, int y){
		int[] temp = new int[2];
		temp[0] = -1;
		int min = n * m;
		for (int i = 0; i < m; i++) {
			for (int j = n-1; j >= 0; j--) {
				if (map[j][i] == 1) {
					int dis = getDis(x, y, j, i);
					if (dis <= d && min > dis) {
						min = dis;
						temp[0] = j;
						temp[1] = i;
						break;
					}
				}
			}
		}
		return temp;
	}
	//맨허튼 거리 리턴
	static int getDis(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	//원래 상태로 배열 복원
	static void back() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = 0;
				if (save[i][j]) map[i][j] = 1;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n + 1][m];
		save = new boolean[n][m];
		visited = new boolean[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) save[i][j] = true;
			}
		}
		comb(0);
		System.out.println(answer);
	}
}