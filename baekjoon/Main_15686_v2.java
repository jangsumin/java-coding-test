// 치킨 배달

package baekjoon;

import java.io.*;
import java.util.*;

// 치킨 집 좌표 저장하고
// 내가 원하는 개수만큼의 조합만 생성
// 각 집에서 가장 가까운 치킨집과 거리 계산

public class Main_15686_v2 {
	static int N, M;
	static int[][] map;
	static boolean[] v;
	static int[][] chicken_location;
	static int min_result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		int chicken_cnt = 0;
		// map 채우기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chicken_cnt++;
			}
		}
		
		// 조합을 위한 배열과, 치킨 위치 정보 담은 배열 생성
		v = new boolean[chicken_cnt];
		chicken_location = new int[chicken_cnt][2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2)  {
					chicken_location[idx][0] = i;
					chicken_location[idx][1] = j;
					idx++;
				}
			}
		}
		
		// for (int[] row : chicken_location) System.out.println(Arrays.toString(row));
		
		comb(0, 0);
		
		System.out.println(min_result);
	}
	
	static void comb(int cnt, int start) {
		if (cnt == M) {
			// 여기서 이제 최단 거리를 구하는 로직을 세우면 됨
			// System.out.println(Arrays.toString(v));
			
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						sum += shortestChicken(i, j);
					}
				}
			}
			
			min_result = Math.min(min_result, sum);
			
			return;
		}
		
		for (int i = start; i < chicken_location.length; i++) {
			v[i] = true;
			comb(cnt + 1, i + 1);
			v[i] = false;
		}
	}
	
	static int shortestChicken(int x, int y) {
		int min_length = Integer.MAX_VALUE;
		for (int i = 0; i < chicken_location.length; i++) {
			if (!v[i]) continue;
			int len = Math.abs(chicken_location[i][0] - x) + Math.abs(chicken_location[i][1] - y);
			min_length = Math.min(min_length, len);
		}
		return min_length;
	}

}
