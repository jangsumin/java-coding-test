package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16926 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int save_N = N;
		int save_M = M;
		int cnt = Math.min(save_N, save_M) / 2;
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 제일 바깥 줄의 길이만큼 q 생성해서 R만큼 돌린 걸 다시 원래 map에 할당하고
		// 또 다시 안으로 들어가서 위에 과정을 반복한 다음에 map에 할당하고
		// 종료 조건 자체는 N이나 M이 2가 되면 종료
		
		int start_i = 0;
		int start_j = 0;
		int idx = 0;
		
		while (true) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			for (int j = start_j; j < M; j++) {
				q.offer(map[start_i][j]);
			}
			q.pollLast();
			for (int i = start_i; i < N; i++) {
				q.offer(map[i][M - 1]);
			}
			q.pollLast();
			for (int j = M - 1; j >= start_j; j--) {
				q.offer(map[N - 1][j]);
			}
			q.pollLast();
			for (int i = N - 1; i >= start_i; i--) {
				q.offer(map[i][start_j]);
			}
			q.pollLast();
			
			// 회전하기
			for (int i = 0; i < R; i++) q.offer(q.poll());
			
			// System.out.println(q.toString());
			
			int save = 0;
			
			save = q.poll();
			q.offer(save);
			q.offerFirst(save);
			for (int j = start_j; j < M; j++) {
				save = q.poll();
				map[start_i][j] = save;
			}
			q.offerFirst(save);
			for (int i = start_i; i < N; i++) {
				save = q.poll();
				map[i][M - 1] = save;
			}
			q.offerFirst(save);
			for (int j = M - 1; j >= start_j; j--) {
				save = q.poll();
				map[N - 1][j] = save;
			}
			q.offerFirst(save);
			// System.out.println(q.toString());
			for (int i = N - 1; i >= start_i; i--) {
				save = q.poll();
				map[i][start_j] = save;
			}
			
			// break;
//		
			start_i++; start_j++; N--; M--; idx++;
			if (idx == cnt) break;
		}
		
		// for (int[] row : map) System.out.println(Arrays.toString(row));
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < save_N; i++) {
			for (int j = 0; j < save_M; j++) {
				if (j != save_M - 1) sb.append(map[i][j]).append(" ");
				else sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}
