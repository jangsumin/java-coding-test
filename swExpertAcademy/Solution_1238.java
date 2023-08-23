package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1238 {
	static List<Integer>[] adjList;
	static int[] v = new int[101];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int len = Integer.parseInt(st.nextToken()); // 24
			int start = Integer.parseInt(st.nextToken()); // 2
			int[] data = new int[len];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < len; i++) data[i] = Integer.parseInt(st.nextToken());
			
			// 인접 리스트와 큐로 bfs
			adjList = new ArrayList[101];
			
			for (int i = 0; i < 101; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			// 내가 만들어야 하는 관계의 수는 데이터의 길이의 절반
			for (int i = 0; i < len; i++) {
				if (i % 2 == 0) {
					adjList[data[i]].add(data[i + 1]);
				}
			}
			
			for (int i = 0; i < 101; i++) v[i] = -1;
			
			// for (List el : adjList) System.out.println(el.toString());
			
			bfs(start);	
			// System.out.println(Arrays.toString(v));
			
			int result = 101;
			int max_result = -1;
			for (int i = 100; i >= 0; i--) {
				if (v[i] != -1) {
					if (v[i] > max_result) {
						max_result = v[i];
						result = i;
					}
				}
			}
			
			System.out.println(String.format("#%d %d", tc, result));
		}
	}
	
	static void bfs(int node) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		v[node] = 0;
		q.offer(node);
		while (!q.isEmpty()) {
			int poll_q = q.poll();
			for (int el : adjList[poll_q]) {
				if (v[el] == -1) {
					v[el] = v[poll_q] + 1;
					q.offer(el);
				}
			}
		}
	}

}
