// 프로세서 연결하기

package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1767 {
	static int min_result = Integer.MAX_VALUE; // 결과가 될 전선의 길이의 최소합
	static int max_connect = 0; // 가장 많은 연결 개수 중에 전선의 길이의 최소합을 구해야 함.
	static int N; // map의 가로, 세로 길이
	static int[][] map;
	static int[][] cores; // 가장자리를 제외한 core들의 위치 저장
	static boolean[] v; // 부분집합 생성을 위한 boolean 배열
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			min_result = Integer.MAX_VALUE; // 테스트 케이스마다 min_result와 max_connect 초기화 필요
			max_connect = 0;
			
			int core_cnt = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && (i != 0 && i != N - 1 && j != 0 && j != N - 1)) core_cnt++; // 가장자리에 위치한 코어 제외한 코어 개수 계산
				}
			}
			
			v = new boolean[core_cnt]; // 코어 개수를 기반으로 부분집합의 원소 크기와 코어 위치 정보 저장할 이차원 배열의 길이 지정
			cores = new int[core_cnt][2];
			
			int idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && (i != 0 && i != N - 1 && j != 0 && j != N - 1)) {
						cores[idx][0] = i; // 코어 위치 정보 저장
						cores[idx][1] = j;
						idx++;
					}
				}
			}
			// for (int[] el : cores) System.out.println(Arrays.toString(el));
			
			subset(0);
			
			sb.append("#").append(tc).append(" ").append(min_result).append("\n");	
		}
		System.out.println(sb.toString());
	}
	
	static void subset(int cnt) {
		if (cnt == cores.length) { // 부분집합의 원소의 true or false 처리를 코어 개수만큼 했으면 부분집합 완성
			// System.out.println(Arrays.toString(v));
			dfs(0, 0);
			return;
		}
		
		v[cnt] = true;
		subset(cnt + 1);
		v[cnt] = false;
		subset(cnt + 1);
	}
	
	// 이미 코어들의 x, y 좌표들은 cores 배열에 담았으므로 idx만 변경하면 됨.
	static void dfs(int idx, int length) {
		if (idx == cores.length) { // 코어를 모두 고려한 경우에 dfs 탈출해야 함
			int conn = 0;
			for (boolean el : v) if (el) conn++;
			
			if (conn > max_connect) { // 연결이 기존의 경우보다 더 많을 경우에는 min_result 값 갱신 필요 없음
				max_connect = conn;
				min_result = length;
			}
			if (conn == max_connect) { // 연결이 기존의 경우와 같을 때는 갱신해줘야 함.
				min_result = Math.min(min_result, length);				
			}
			
			// 출력 확인문
			// for (int[] el : map) System.out.println(Arrays.toString(el));
			// System.out.println("길이 : " + length);
			// System.out.println("연결 개수 : " + conn);
			// System.out.println();
			return;
		}
		
		if(!v[idx]) { // 코어를 고려하지 않아도 되는 false의 경우에는 다음 코어로 넘어감
			dfs(idx + 1, length);
			return;
		}
		
		for (int i = 0; i < 4; i++) { // 상, 우, 하, 좌 방향을 탐색해야 함
			int x = cores[idx][0]; // 이번 dfs 연산에 해당하는 코어의 위치 정보 가져오기
			int y = cores[idx][1];
			boolean isConnected = false;
			int line = 0;
			
			while (true) {
				x += dx[i];
				y += dy[i];
				if (x < 0 || x >= N || y < 0 || y >= N) {
					isConnected = true; // 코어의 위치에서부터 상, 우, 하, 좌 방향 중 한 방향으로 쭉 이동하다가 map의 범위를 나가면 연결 성공
					break;
				}
				if (map[x][y] != 0) break; // 중간에 가다가 전선을 만나거나, 코어를 만나면 break
				map[x][y] = 2; // 전선이 깔렸다는 얘기
				line++; // 전선의 길이 저장
			}
			
			if(isConnected) dfs(idx + 1, length + line); // 연결이 된 경우에는 다음 코어를 고려
			while (true) { // 위 while 문에서 map 원본을 수정하였으므로 다음 부분집합의 경우에는 원상태로 복구 필요
				x -= dx[i];
				y -= dy[i];
				if (x == cores[idx][0] && y == cores[idx][1]) break;
				map[x][y] = 0;
			}
		}		
	}
}
