package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1697 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[100001];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(N);
		while (!q.isEmpty()) {
			int n = q.poll();
			if (n == K) break;
			
			if (n + 1 >= 0 && n + 1 <= 100000 && arr[n + 1] == 0) {
				arr[n + 1] = arr[n] + 1;
				q.offer(n + 1);
			}
			if (n - 1 >= 0 && n - 1 <= 100000 && arr[n - 1] == 0) {
				arr[n - 1] = arr[n] + 1;
				q.offer(n - 1);
			}
			if (n * 2 >= 0 && n * 2 <= 100000 && arr[n * 2] == 0) {
				arr[n * 2] = arr[n] + 1;
				q.offer(n * 2);
			}
		}
		System.out.println(arr[K]);
	}
	
	// 5, 4 6 10, 3 5 8 5 7 12 9 11 20, 
	
	// 재귀 사용하면 스택 오버플로우 남
//	static void recur(int n, int k, int cnt) {
//		if (n == k) {
//			answer = cnt;
//			return;
//		}
//		
//		recur(n - 1, k, cnt + 1);
//		recur(n + 1, k, cnt + 1);
//		recur(n * 2, k, cnt + 1);
//	}

}
