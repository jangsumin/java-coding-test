package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17406 {
	static int N, M, K;
	static int[][] map, cycle;
	static boolean[] v;
	static int[][] save_idx;
	static int min_result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[K];
		save_idx = new int[K][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cycle = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cycle[i][0] = Integer.parseInt(st.nextToken());
			cycle[i][1] = Integer.parseInt(st.nextToken());
			cycle[i][2] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		System.out.println(min_result);
	}
	
	// map 복사하기 - 순열에 의해 여러 번 돌려야 하니까 원본을 복사해야 함
	static int[][] copyMap() {
	    int[][] tmp = new int[N][M];
	    for(int i=0; i<N; i++) {
	        for(int j=0; j<M; j++) {
	            tmp[i][j] = map[i][j];
	        }
	    }
	    return tmp;
	}
	
	static void perm(int cnt) {
		if (cnt == K) {
			// for (int[] el : save_idx) System.out.println(Arrays.toString(el));
			doCycle(save_idx);
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (v[i]) continue;
			v[i] = true;
			save_idx[cnt] = cycle[i];
			perm(cnt + 1);
			v[i] = false;
		}
	}
	
	static void doCycle(int[][] arr) {
		// 2차원 배열 arr에 담긴 정보를 가지고 그대로 실행
		int[][] tmp = copyMap();
		int min_row = Integer.MAX_VALUE;
		
		// arr -> [[3, 4, 2], [4, 2, 1]]
		for (int i = 0; i < arr.length; i++) {
			int R = arr[i][0] - 1;
			int C = arr[i][1] - 1;
			int S = arr[i][2];
			
			for (int s = S; s >= 1; s--) {
				
				// 윗변
				// 오른쪽 위 좌표 저장
				int up_tmp = tmp[R - s][C + s];
				for (int y = C + s; y > C - s; y--) {
					tmp[R - s][y] = tmp[R - s][y - 1];
				}
				
				// 오른쪽 변
				int right_tmp = tmp[R + s][C + s];
				for (int x = R + s; x > R - s; x--) {
					tmp[x][C + s] = tmp[x - 1][C + s];
				}
				tmp[R - s + 1][C + s] = up_tmp;
				
				int left_tmp = tmp[R + s][C - s];
				for (int y = C - s; y < C + s; y++) {
					tmp[R + s][y] = tmp[R + s][y + 1];
				}
				tmp[R + s][C + s - 1] = right_tmp;
				
				for (int x = R - s; x < R + s; x++) {
					tmp[x][C - s] = tmp[x + 1][C - s];
				}
				tmp[R + s - 1][C - s] = left_tmp;
				
				// for (int[] row : tmp) System.out.println(Arrays.toString(row));
				// System.out.println();
			}
			
		}
		
		for (int j = 0; j < N; j++) {
			int row_sum = 0;
			for (int k = 0; k < M; k++) {
				row_sum += tmp[j][k];
			}
			min_row = Math.min(min_row, row_sum);
		}
		
		min_result = Math.min(min_row, min_result);			
	}
}
