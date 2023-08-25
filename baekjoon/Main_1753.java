package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1753 {
	static int V, E, K;
	static List<int[]>[] adjList;
	static boolean[] v;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		adjList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[u - 1].add(new int[] {v - 1, w});
		}
		
//		for (List<int[]> el : adjList) {
//			for (int[] el2 : el) System.out.print(Arrays.toString(el2) + " ");
//			System.out.println();
//		}
		dist = new int[V];
		v = new boolean[V];
		for (int i = 0; i < V; i++) dist[i] = Integer.MAX_VALUE; // 모두 무한대로 초기화
		
		dist[K - 1] = 0; // 시작 지점만 0으로 바꾸고 데이크스트라 알고리즘 시작
		dijkstra(K - 1, 0); // 1번 정점에서 시작
		
		for (int el : dist) {
			if(el == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(el);
		}
		br.close();
	}
	
	static void dijkstra(int node, int cost) {
		// 가중치 기준으로 우선 순위 셋
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.offer(new int[] {node, cost});
		while (!pq.isEmpty()) {
			int[] pq_poll = pq.poll();
			int node_num = pq_poll[0];
			int min_cost = pq_poll[1];
			
			v[node_num] = true;
			for (int[] info : adjList[node_num]) {
				int to = info[0];
				int weight = info[1];
				if (!v[to] && dist[to] > min_cost + weight) {
					dist[to] = weight + min_cost;
					pq.offer(new int[] {to, dist[to]}); // [1, 2], [2, 3] 이 순서로 들어가 있음
				}
			}
		}
	}

}
